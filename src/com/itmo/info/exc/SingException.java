package com.itmo.info.exc;

// unchecked exception
public class SingException extends RuntimeException {

    public SingException() {
        super();
    }

    public SingException(String message) {
        super(message);
    }

}
