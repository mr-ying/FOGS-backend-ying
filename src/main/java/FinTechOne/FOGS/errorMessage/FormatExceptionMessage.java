package FinTechOne.FOGS.errorMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FormatExceptionMessage {

    private final List<FormatError> errors = new ArrayList<FormatError>();
    private final String messageType = "FormatException";

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("errors")
    public List<FormatError> getErrors() {
        return errors;
    }


    public FormatExceptionMessage(InvalidFormatException e){
        for (JsonMappingException.Reference fieldError : e.getPath()) {
            errors.add(FormatError.of(fieldError.getFrom().getClass().getSimpleName(),
                    fieldError.getFieldName(),
                    e.getValue(),
                    e.getTargetType().getSimpleName(),
                    e.getOriginalMessage()
                    )
            );
        }
    }

    @Value(staticConstructor = "of")
    public static class FormatError {
        String entity, property;
        Object invalidValue;
        String code;
        String message;
    }
}
