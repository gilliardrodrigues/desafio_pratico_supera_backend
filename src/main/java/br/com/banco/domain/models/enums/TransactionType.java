package br.com.banco.domain.models.enums;

public enum TransactionType {

    DEPOSITO("Depósito"),
    SAQUE("Saque"),
    TRANSFERENCIA("Transferência");

    private final String description;

    TransactionType(String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;
    }
}

