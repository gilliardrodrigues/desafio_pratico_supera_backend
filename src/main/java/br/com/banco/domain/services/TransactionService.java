package br.com.banco.domain.services;

import br.com.banco.domain.exceptions.AccountIdNotFoundException;
import br.com.banco.domain.exceptions.MissingArgumentException;
import br.com.banco.domain.models.entities.Transaction;
import br.com.banco.domain.repositories.TransactionRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
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

    private boolean existsByAccountId(Long accountId) {

        return transactionRepository.existsByAccountId(accountId);
    }
}
