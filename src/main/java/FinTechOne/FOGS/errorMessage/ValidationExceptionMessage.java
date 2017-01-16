package FinTechOne.FOGS.errorMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.Value;
import org.hibernate.StaleStateException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ValidationExceptionMessage {

    private final List<ValidationError> errors = new ArrayList<ValidationError>();
    private final String messageType = "FieldValidation";

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("errors")
    public List<ValidationError> getErrors() {
        return errors;
    }


    //  Hibernate Validators (JSR303, JSR349)
//  Error code = annotation
    public ValidationExceptionMessage(ConstraintViolationException e, MessageSource messageSource){
        for (ConstraintViolation<?> fieldError : e.getConstraintViolations()) {
            String entityName = fieldError.getRootBeanClass().getSimpleName();
            String propertyName = fieldError.getPropertyPath().toString();
            String annotationName = fieldError.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
            String[] codes = {
                    annotationName + "." + entityName + "." + propertyName,
                    annotationName + "." + entityName,
                    annotationName,
            };
            String defaultMessage = "";
            String exceptionCode = annotationName;
            for (String code : codes){
                try {
                    defaultMessage = messageSource.getMessage(code, null, null);
                    exceptionCode = code;
                    break;
                } catch (NoSuchMessageException noSuchMessageException){
                }
            }
            errors.add(ValidationError.of(
                    entityName,
                    propertyName,
                    fieldError.getInvalidValue(),
                    exceptionCode,
                    defaultMessage
                    )
            );
        }
    }

//  Spring Validators
    public ValidationExceptionMessage(RepositoryConstraintViolationException e, MessageSource messageSource){
        for (ObjectError objectError : e.getErrors().getAllErrors()) {
            FieldError fieldError = (FieldError)objectError;
            String defaultMessage = "";
            String exceptionCode = "";
            for (String code : fieldError.getCodes()){
                exceptionCode = code;
                try {
                    defaultMessage = messageSource.getMessage(code, null, null);
                    break;
                } catch (NoSuchMessageException noSuchMessageException){
               }
            }
            errors.add(
                ValidationError.of(
                    objectError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    exceptionCode,
                    defaultMessage
                )
            );
        }
    }


//    public ValidationExceptionMessage(DataIntegrityViolationException e, MessageSource messageSource){
//        if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException){
//            org.hibernate.exception.ConstraintViolationException cause = (org.hibernate.exception.ConstraintViolationException)e.getCause();
//            String constrainName = cause.getConstraintName();
//            if (constrainName == null){
//                if (cause.getSQLException() instanceof SQLIntegrityConstraintViolationException &&
//                    cause.getSQLException().getMessage().startsWith("Cannot delete or update a parent row: a foreign key constraint fails")) {
//                    errors.add(ValidationError.of("", "", "",
//                            messageSource.getMessage("ForeignKey", null, null),
//                            cause.getSQLException().getMessage()));
//                }
//            }
//            else if (constrainName.startsWith("UKey")){
//                String entityName = constrainName.split("_")[1];
//                if (cause.getSQLException() instanceof SQLIntegrityConstraintViolationException &&
//                    cause.getSQLException().getMessage().startsWith("Duplicate entry")){
//                    errors.add(ValidationError.of(entityName, "", "",
//                            messageSource.getMessage("DupKey", null, null),
//                            cause.getSQLException().getMessage()));
//                }
//            } else {
//                errors.add(ValidationError.of("", "", "",
//                        messageSource.getMessage("DataIntegrity", null, null),
//                        cause.getSQLException().getMessage()));
//
//            }
//        }
//    }


    public ValidationExceptionMessage(StaleStateException e, MessageSource messageSource) {
        errors.add(ValidationError.of("", "", "",
                messageSource.getMessage("RowCount", null, null),
                e.getMessage()));
    }


    @Value(staticConstructor = "of")
    public static class ValidationError {
        String entity, property;
        Object invalidValue;
        String code;
        String message;
    }

}
