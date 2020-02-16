package com.payam.learn.usermanagement.exception;


import org.springframework.stereotype.Component;


import static com.payam.learn.usermanagement.utils.MessageHelper.format;
import static com.payam.learn.usermanagement.utils.MessageHelper.getMessageTemplate;

@Component
public  class CustomException {

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }

    }

    public static class EntityNotFoundException extends IllegalStateException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static RuntimeException throwExceptionWithTemplate(EntityType entityType, ExceptionType exceptionType, String args) {
        if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(getMessageTemplate(entityType, exceptionType), args));
        } else if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(getMessageTemplate(entityType, exceptionType), args));
        }
        return new RuntimeException(format(getMessageTemplate(entityType, exceptionType), args));
    }


}
