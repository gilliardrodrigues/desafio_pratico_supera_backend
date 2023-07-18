package br.com.banco.api.controllers;

import br.com.banco.api.dtos.BankExtractDTO;
import br.com.banco.api.dtos.TransactionDTO;
import br.com.banco.domain.mappers.GenericMapper;
import br.com.banco.domain.models.entities.Account;
import br.com.banco.domain.models.entities.Transaction;
import br.com.banco.domain.models.enums.TransactionType;
import br.com.banco.domain.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.TypeToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionServiceMock;

    @Mock
    private GenericMapper mapper;

    @BeforeEach
    void setUp() {
        var account = createAccount();
        var transaction = createTransaction(account);
        var transactionDTO = new TransactionDTO(OffsetDateTime.now(), BigDecimal.TEN, TransactionType.TRANSFERENCIA, "Luiz", account.getId());
        var transactions = List.of(transaction);

        BDDMockito.when(mapper.mapToList(transactions, new TypeToken<List<TransactionDTO>>() {}.getType()))
                .thenReturn(List.of(transactionDTO));

        List<TransactionDTO> transactionsDTO = mapper.mapToList(transactions, new TypeToken<List<TransactionDTO>>() {}.getType());
        var partialSum = transaction.getValue();
        var totalSum = transaction.getValue();
        var bankExtract = new BankExtractDTO(transactionsDTO, partialSum, totalSum);

        BDDMockito.when(transactionServiceMock.getBankExtract(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(bankExtract);
    }

    @Test
    void getBankExtract_WhenSuccessful() {


        BankExtractDTO bankExtractDTO = transactionController.getBankExtract(anyLong(), any(OffsetDateTime.class), any(OffsetDateTime.class), anyString()).getBody();

        Assertions.assertNotNull(bankExtractDTO);
    }

    private Account createAccount() {

        return Account.builder()
                .responsibleName("Ana")
                .transactions(List.of(new Transaction()))
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