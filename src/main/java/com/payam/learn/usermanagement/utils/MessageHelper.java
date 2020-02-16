package com.payam.learn.usermanagement.utils;

import com.payam.learn.usermanagement.config.PropertiesConfig;
import com.payam.learn.usermanagement.exception.CustomException;
import com.payam.learn.usermanagement.exception.EntityType;
import com.payam.learn.usermanagement.exception.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class MessageHelper {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public MessageHelper(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;

    }

    public static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().toLowerCase().concat(".").concat(exceptionType.getValue().toLowerCase());
    }

    public static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), args);
        }
        return String.format(template, args);
    }
}
