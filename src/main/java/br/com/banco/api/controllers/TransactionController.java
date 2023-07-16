package br.com.banco.api.controllers;

import br.com.banco.api.dtos.TransactionDTOResponse;
import br.com.banco.domain.mappers.GenericMapper;
import br.com.banco.domain.services.TransactionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transactions")
@AllArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Banco API - Processo Seletivo Supera", version = "1.0"))
public class TransactionController {

    private final TransactionService transactionService;
    private final GenericMapper mapper;

    @Tag(name = "Transações")
    @GetMapping
    @Operation(summary = "Lista todas as transferências de acordo com os parâmetros informados.")
    public ResponseEntity<List<TransactionDTOResponse>> listTransactions(
            @RequestParam(required = false) @NotNull Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDate,
            @RequestParam(required = false) String transactionOperatorName) {

        var transactions = transactionService.findAllByParameters(accountId, startDate, endDate, transactionOperatorName);
        return ResponseEntity.ok(mapper.mapToList(transactions, new TypeToken<List<TransactionDTOResponse>>() {}.getType()));
    }
}
