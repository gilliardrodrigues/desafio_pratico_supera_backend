package br.com.banco.api.dtos;

import br.com.banco.domain.models.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime date;
    private BigDecimal value;
    private TransactionType type;
    private String transactionOperatorName;
    private Long accountId;
}
