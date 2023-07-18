package br.com.banco.domain.services;

import br.com.banco.domain.exceptions.AccountIdNotFoundException;
import br.com.banco.domain.exceptions.MissingArgumentException;
import br.com.banco.domain.mappers.GenericMapper;
import br.com.banco.domain.models.entities.Transaction;
import br.com.banco.domain.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepositoryMock;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testFindAllByParameters_WithValidData() {
        Long accountId = 99L;
        OffsetDateTime startDate = OffsetDateTime.now().minusDays(7);
        OffsetDateTime endDate = OffsetDateTime.now();
        String transactionOperatorName = "João";

        List<Transaction> expectedTransactions = new ArrayList<>();

        Specification<Transaction> expectedSpec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(root.get("account").get("id"), accountId));

            predicates.add(builder.equal(root.get("transactionOperatorName"), transactionOperatorName));

            predicates.add(builder.greaterThanOrEqualTo(root.get("date"), startDate));

            predicates.add(builder.lessThanOrEqualTo(root.get("date"), endDate));

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        Mockito.when(transactionService.existsByAccountId(accountId))
                .thenReturn(true);

        Mockito.when(transactionRepositoryMock.findAll(expectedSpec))
                .thenReturn(expectedTransactions);

        List<Transaction> transactions = transactionService.findAllByParameters(accountId, startDate, endDate, transactionOperatorName);

        Assertions.assertEquals(expectedTransactions, transactions);
    }

    @Test
    void testGetBankExtract_WithMissingAccountId() {
        
        Long accountId = null;
        OffsetDateTime startDate = OffsetDateTime.now().minusDays(7);
        OffsetDateTime endDate = OffsetDateTime.now();
        String transactionOperatorName = "João";
        
        Assertions.assertThrows(MissingArgumentException.class, () ->
                transactionService.getBankExtract(accountId, startDate, endDate, transactionOperatorName));
    }

    @Test
    void testGetBankExtract_WithInvalidAccountId() {
        
        Long accountId = 1L;
        OffsetDateTime startDate = OffsetDateTime.now().minusDays(7);
        OffsetDateTime endDate = OffsetDateTime.now();
        String transactionOperatorName = "João";

        when(transactionService.existsByAccountId(accountId))
                .thenReturn(false);
        
        Assertions.assertThrows(AccountIdNotFoundException.class, () ->
                transactionService.getBankExtract(accountId, startDate, endDate, transactionOperatorName));
    }

    @Test
    void testFindAllByParameters() {
        
        Long accountId = 1L;
        OffsetDateTime startDate = OffsetDateTime.now().minusDays(7);
        OffsetDateTime endDate = OffsetDateTime.now();
        String transactionOperatorName = "João";

        List<Transaction> expectedTransactions = new ArrayList<>();

        Specification<Transaction> expectedSpec = (root, query, builder) -> {
            return null;
        };

        when(transactionService.existsByAccountId(accountId))
                .thenReturn(true);

        when(transactionRepositoryMock.findAll(expectedSpec))
                .thenReturn(expectedTransactions);
        
        List<Transaction> transactions = transactionService.findAllByParameters(accountId, startDate, endDate, transactionOperatorName);
        
        Assertions.assertEquals(expectedTransactions, transactions);
    }

    @Test
    void testSumTransactionValues() {
        
        Transaction transaction1 = Transaction.builder().value(BigDecimal.valueOf(100.00)).build();
        Transaction transaction2 = Transaction.builder().value(BigDecimal.valueOf(200.00)).build();
        Transaction transaction3 = Transaction.builder().value(BigDecimal.valueOf(300.00)).build();
        List<Transaction> transactions = List.of(transaction1, transaction2, transaction3);
        
        BigDecimal sum = transactionService.sumTransactionValues(transactions);
        
        Assertions.assertEquals(BigDecimal.valueOf(600.00), sum);
    }

    @Test
    void testExistsByAccountId() {
        
        Long accountId = 1L;

        when(transactionRepositoryMock.existsByAccountId(accountId))
                .thenReturn(true);
        
        boolean exists = transactionService.existsByAccountId(accountId);
        
        Assertions.assertTrue(exists);
    }
}