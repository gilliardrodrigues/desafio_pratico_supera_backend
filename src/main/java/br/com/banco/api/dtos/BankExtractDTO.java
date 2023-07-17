package br.com.banco.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankExtractDTO {

    private List<TransactionDTO> transactions;
    private BigDecimal partialSum;
    private BigDecimal totalSum;
}
