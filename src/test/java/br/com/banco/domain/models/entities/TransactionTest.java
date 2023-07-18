package br.com.banco.domain.models.entities;

import br.com.banco.domain.models.enums.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testEquals() {

        Transaction transaction = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Transaction otherTransaction = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Assertions.assertEquals(transaction, otherTransaction);
    }

    @Test
    void testHashCode() {

        Transaction transaction = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Transaction otherTransaction = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Assertions.assertEquals(transaction.hashCode(), otherTransaction.hashCode());
    }

    @Test
    void testGettersAndSetters() {

        Transaction transaction = new Transaction();

        transaction.setId(2L);
        Assertions.assertEquals(2L, transaction.getId());

        OffsetDateTime newDate = OffsetDateTime.MIN.plusYears(100);
        transaction.setDate(newDate);
        Assertions.assertEquals(newDate, transaction.getDate());

        BigDecimal newValue = BigDecimal.valueOf(200.00);
        transaction.setValue(newValue);
        Assertions.assertEquals(newValue, transaction.getValue());

        TransactionType newType = TransactionType.DEPOSITO;
        transaction.setType(newType);
        Assertions.assertEquals(newType, transaction.getType());

        String newOperatorName = "Luiz";
        transaction.setTransactionOperatorName(newOperatorName);
        Assertions.assertEquals(newOperatorName, transaction.getTransactionOperatorName());

        Account newAccount = new Account();
        newAccount.setId(2L);
        newAccount.setResponsibleName("Gabriel");
        transaction.setAccount(newAccount);
        Assertions.assertEquals(newAccount, transaction.getAccount());
    }

    @Test
    void testEquals_SameObject_ReturnsTrue() {

        Transaction transaction = Transaction.builder()
                .id(10L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();
        assertEquals(transaction, transaction);
    }

    @Test
    void testEquals_NullObject_ReturnsFalse() {

        Transaction transaction = Transaction.builder()
                .id(10L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();
        assertNotEquals(null, transaction);
    }

    @Test
    void testEquals_DifferentClass_ReturnsFalse() {

        Transaction transaction = Transaction.builder()
                .id(10L)
                .date(OffsetDateTime.MIN)
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();
        assertNotEquals("Teste", transaction);
    }

    @Test
    void testEquals_EqualObjects_ReturnsTrue() {

        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        assertEquals(transaction1, transaction2);
        assertEquals(transaction2, transaction1);
    }

    @Test
    void testEquals_DifferentIds_ReturnsFalse() {

        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        assertNotEquals(transaction1, transaction2);
        assertNotEquals(transaction2, transaction1);
    }

    @Test
    void testEquals_DifferentDates_ReturnsFalse() {

        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now().minusDays(1))
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        assertNotEquals(transaction1, transaction2);
        assertNotEquals(transaction2, transaction1);
    }

    @Test
    void testEquals_DifferentObjects_ReturnsFalse() {

        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        Transaction transaction2 = Transaction.builder()
                .id(2L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(200.00))
                .type(TransactionType.SAQUE)
                .transactionOperatorName("Maria")
                .account(new Account())
                .build();

        assertNotEquals(transaction1, transaction2);
        assertNotEquals(transaction2, transaction1);
    }
    
    @Test
    void testToString() {
        Transaction transaction = Transaction.builder()
                .id(1L)
                .date(OffsetDateTime.now())
                .value(BigDecimal.valueOf(100.00))
                .type(TransactionType.TRANSFERENCIA)
                .transactionOperatorName("João")
                .account(new Account())
                .build();

        String expectedToString = "Transaction{" +
                "id=" + transaction.getId() +
                ", date=" + transaction.getDate() +
                ", value=" + transaction.getValue() +
                ", type=" + transaction.getType() +
                ", transactionOperatorName='" + transaction.getTransactionOperatorName() + '\'' +
                ", account=" + transaction.getAccount() +
                '}';

        assertEquals(expectedToString, transaction.toString());
    }

}