package br.com.banco.domain.models.entities;

import br.com.banco.domain.models.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSFERENCIA")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DATA_TRANSFERENCIA", nullable = false)
    private OffsetDateTime date;

    @Column(name = "VALOR", nullable = false, scale = 2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private TransactionType type;

    @Column(name = "NOME_OPERADOR_TRANSACAO")
    private String transactionOperatorName;

    @ManyToOne
    @JoinColumn(name = "CONTA_ID", nullable = false)
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(value, that.value) && type == that.type && Objects.equals(transactionOperatorName, that.transactionOperatorName) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, value, type, transactionOperatorName, account);
    }
}
