package br.com.banco.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingArgumentException extends BusinessLogicException {

    public MissingArgumentException(String message) {

        super(message);
    }
}
