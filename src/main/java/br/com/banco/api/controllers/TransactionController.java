package br.com.banco.api.controllers;

import br.com.banco.api.dtos.BankExtractDTO;
import br.com.banco.domain.services.TransactionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transactions")
@AllArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Banco API - Processo Seletivo Supera", version = "1.0"))
public class TransactionController {

    private final TransactionService transactionService;

    @Tag(name = "Transações")
    @GetMapping
    @Operation(summary = "Retorna o extrato bancário, com as transações filtradas de acordo com os parâmetros informados, bem como a soma parcial e total dos valores.")
    public ResponseEntity<BankExtractDTO> getBankExtract(
            @RequestParam(required = false) @NotNull Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDate,
            @RequestParam(required = false) String transactionOperatorName) {

       var bankExtract = transactionService.getBankExtract(accountId, startDate, endDate, transactionOperatorName);

        return ResponseEntity.ok(bankExtract);
    }
}
