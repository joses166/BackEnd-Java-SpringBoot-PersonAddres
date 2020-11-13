package br.com.josehamilton.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WaitingValuesException extends RuntimeException {



    public WaitingValuesException(String s) {
        super(s);
    }

}
