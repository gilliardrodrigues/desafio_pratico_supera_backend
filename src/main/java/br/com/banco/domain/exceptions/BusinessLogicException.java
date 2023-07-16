package br.com.banco.domain.exceptions;

public class BusinessLogicException extends RuntimeException {

    public BusinessLogicException(String message) {

        super(message);
    }
}
