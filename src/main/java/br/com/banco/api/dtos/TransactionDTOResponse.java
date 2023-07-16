package br.com.banco.api.dtos;

import br.com.banco.domain.models.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class TransactionDTOResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime date;
    private BigDecimal value;
    private TransactionType type;
    private String transactionOperatorName;
    private Long accountId;
}
