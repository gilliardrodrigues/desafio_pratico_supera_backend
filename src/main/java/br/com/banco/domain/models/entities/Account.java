package br.com.banco.domain.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTA")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CONTA", nullable = false)
    private Long id;

    @Column(name = "NOME_RESPONSAVEL", nullable = false)
    private String responsibleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(responsibleName, account.responsibleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responsibleName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "responsibleName = " + responsibleName + ")";
    }
}
