package br.com.banco.domain.repositories;

import br.com.banco.domain.models.entities.Account;
import br.com.banco.domain.models.entities.Transaction;
import br.com.banco.domain.models.enums.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@DataJpaTest
@DisplayName(value = "Testes para o TransactionRepository")
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;
    private Transaction transaction;

    @BeforeEach
    void setup() {
        accountRepository.deleteAll();
        transactionRepository.deleteAll();

        account = createAccount();
        accountRepository.save(account);

        transaction = createTransaction(account);
        transactionRepository.save(transaction);
    }

    @Test
    void existsByAccountId_ReturnsTrue_WhenSuccessful() {

        Long accountId = transaction.getAccount().getId();

        boolean exists = transactionRepository.existsByAccountId(accountId);

        Assertions.assertTrue(exists);
    }

    private Account createAccount() {

        return Account.builder()
                .responsibleName("Ana")
                .build();
    }

    private Transaction createTransaction(Account account) {

        return Transaction.builder()
                .date(OffsetDateTime.now())
                .value(BigDecimal.TEN)
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("Luiz")
                .account(account)
                .build();
    }
}