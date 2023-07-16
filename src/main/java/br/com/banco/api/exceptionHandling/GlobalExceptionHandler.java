package br.com.banco.api.exceptionHandling;

import br.com.banco.domain.exceptions.AccountIdNotFoundException;
import br.com.banco.domain.exceptions.MissingArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountIdNotFoundException.class)
    public ResponseEntity<?> handleAccountIdNotFoundException(AccountIdNotFoundException exception) {

        ErrorDetails exceptionDetails = ErrorDetails.Builder
                .newBuilder()
                .dateTime(OffsetDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Conta bancária não encontrada!")
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity<?> handleMissingArgumentException(MissingArgumentException exception) {

        ErrorDetails exceptionDetails = ErrorDetails.Builder
                .newBuilder()
                .dateTime(OffsetDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Campo obrigatório não informado!")
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
