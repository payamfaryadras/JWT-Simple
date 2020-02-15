package com.payam.learn.usermanagement.exception;

import com.payam.learn.usermanagement.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public  class CustomException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public CustomException(PropertiesConfig propertiesConfig){
        CustomException.propertiesConfig = propertiesConfig;
    }

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


    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue().toLowerCase());
    }

    private static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), args);
        }
        return String.format(template, args);
    }
}
