package br.com.banco.domain.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        account1 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();

        account2 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();
    }

    @Test
    void testEquals() {
        assertEquals(account1, account2);
    }

    @Test
    void testHashCode() {
        assertEquals(account1.hashCode(), account2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Account(id = 1, responsibleName = João)";
        assertEquals(expected, account1.toString());
    }

    @Test
    void testGettersAndSetters() {
        account1.setId(2L);
        account1.setResponsibleName("Maria");

        assertEquals(2L, account1.getId());
        assertEquals("Maria", account1.getResponsibleName());
    }

    @Test
    void testTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = Transaction.builder()
                .id(1L)
                .build();
        transactions.add(transaction);

        account1.setTransactions(transactions);
        transaction.setAccount(account1); // Definir a conta associada à transação

        assertEquals(transactions, account1.getTransactions());
        assertEquals(account1, transaction.getAccount());
    }

    @Test
    void testEquals_SameObject_ReturnsTrue() {
        Account account = new Account();
        assertEquals(account, account);
    }

    @Test
    void testEquals_NullObject_ReturnsFalse() {
        Account account = new Account();
        assertNotEquals(null, account);
    }

    @Test
    void testEquals_DifferentClass_ReturnsFalse() {
        Account account = new Account();
        assertNotEquals("Not an Account object", account);
    }

    @Test
    void testEquals_EqualObjects_ReturnsTrue() {
        Account account1 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();

        Account account2 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();

        assertEquals(account1, account2);
        assertEquals(account2, account1);
    }

    @Test
    void testEquals_DifferentIds_ReturnsFalse() {
        Account account1 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();

        Account account2 = Account.builder()
                .id(2L)
                .responsibleName("João")
                .build();

        assertNotEquals(account1, account2);
        assertNotEquals(account2, account1);
    }

    @Test
    void testEquals_DifferentResponsibleNames_ReturnsFalse() {
        Account account1 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();

        Account account2 = Account.builder()
                .id(1L)
                .responsibleName("Maria")
                .build();

        assertNotEquals(account1, account2);
        assertNotEquals(account2, account1);
    }

    @Test
    void testEquals_DifferentObjects_ReturnsFalse() {
        Account account1 = Account.builder()
                .id(1L)
                .responsibleName("João")
                .build();

        Account account2 = Account.builder()
                .id(2L)
                .responsibleName("Maria")
                .build();

        assertNotEquals(account1, account2);
        assertNotEquals(account2, account1);
    }

}
