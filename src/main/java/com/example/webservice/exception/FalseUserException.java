package com.example.webservice.exception;





import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class FalseUserException extends RuntimeException {
    String function;

    public FalseUserException(String function) {
        super(String.format("you can only %s your own posts", function));
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
