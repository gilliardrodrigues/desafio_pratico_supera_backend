package br.com.banco.domain.services;

import br.com.banco.api.dtos.BankExtractDTO;
import br.com.banco.api.dtos.TransactionDTO;
import br.com.banco.domain.exceptions.AccountIdNotFoundException;
import br.com.banco.domain.exceptions.MissingArgumentException;
import br.com.banco.domain.mappers.GenericMapper;
import br.com.banco.domain.models.entities.Transaction;
import br.com.banco.domain.repositories.TransactionRepository;
import org.modelmapper.TypeToken;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final GenericMapper mapper;

    public TransactionService(TransactionRepository transactionRepository, GenericMapper mapper) {

        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    public BankExtractDTO getBankExtract(Long accountId, OffsetDateTime startDate, OffsetDateTime endDate, String transactionOperatorName) {

        var allTransactions = findAllByParameters(accountId, null, null, null);
        var totalSum = sumTransactionValues(allTransactions);
        var filteredTransactions = findAllByParameters(accountId, startDate, endDate, transactionOperatorName);
        var partialSum = sumTransactionValues(filteredTransactions);
        List<TransactionDTO> filteredTransactionsDTO = mapper.mapToList(filteredTransactions, new TypeToken<List<TransactionDTO>>() {}.getType());

        return new BankExtractDTO(filteredTransactionsDTO, partialSum, totalSum);
    }

    public List<Transaction> findAllByParameters(Long accountId, OffsetDateTime startDate, OffsetDateTime endDate, String transactionOperatorName) {

        if(accountId == null)
            throw new MissingArgumentException("Para buscar as transações é necessário informar pelo menos o número da conta bancária!");

        if(!existsByAccountId(accountId))
            throw new AccountIdNotFoundException("Número de conta bancária não encontrado!");

        Specification<Transaction> spec = (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(root.get("account").get("id"), accountId));

            // Verificando se o nome foi informado:
            if (transactionOperatorName != null) {
                predicates.add(builder.equal(root.get("transactionOperatorName"), transactionOperatorName));
            }

            // Verificando se a data de início foi informada:
            if (startDate != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("date"), startDate));
            }

            // Verificando se a data de fim foi informada:
            if (endDate != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("date"), endDate));
            }

            // Combinando todas os predicados usando o operador AND:
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return transactionRepository.findAll(spec);
    }

    protected BigDecimal sumTransactionValues(List<Transaction> transactions) {

        return transactions.stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected boolean existsByAccountId(Long accountId) {

        return transactionRepository.existsByAccountId(accountId);
    }
}
