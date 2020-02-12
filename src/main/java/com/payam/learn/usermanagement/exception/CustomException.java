package com.payam.learn.usermanagement.exception;

import org.springframework.stereotype.Component;

@Component
public final class CustomException {
    //TODO
    public static class  DuplicateEntityException extends RuntimeException{
        public DuplicateEntityException(String message) {
            super(message);
        }
    }


    public static class IllegalEntityStateException extends IllegalStateException{
        public IllegalEntityStateException(String message){
            super(message);
        }
    }
}
