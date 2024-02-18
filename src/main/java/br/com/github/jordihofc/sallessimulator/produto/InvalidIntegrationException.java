package br.com.github.jordihofc.sallessimulator.produto;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidIntegrationException extends RuntimeException{

    public InvalidIntegrationException(String message) {
        super(message);
    }
}
