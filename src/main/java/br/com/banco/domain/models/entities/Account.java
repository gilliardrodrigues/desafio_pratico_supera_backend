package br.com.banco.domain.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTA")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_CONTA", nullable = false)
    private Long id;

    @Column(name = "NOME_RESPONSAVEL", nullable = false)
    private String responsibleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return getId() != null && Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "responsibleName = " + responsibleName + ")";
    }
}
