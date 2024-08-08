package com.newprocess;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
@Slf4j
public class ResourceNotFoundException extends Throwable {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
        notValid(message);
    }

    public String notValid(String message) {
        return "Not a valid Input" + message;
    }
}