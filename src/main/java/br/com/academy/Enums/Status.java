package br.com.academy.Enums;

public enum Status {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    TRANCADO("Trancado"),
    CANCELADO("Cancelado");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
