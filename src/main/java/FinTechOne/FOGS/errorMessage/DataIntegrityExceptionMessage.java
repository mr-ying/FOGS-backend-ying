package FinTechOne.FOGS.errorMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by User on 13/1/2017.
 */
public class DataIntegrityExceptionMessage {
    private String messageType;
    private String message;

    @JsonProperty("messageType")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }


    public DataIntegrityExceptionMessage(DataIntegrityViolationException e){
        this.messageType = "DataIntegrity";
        if (e.getCause() instanceof ConstraintViolationException){
            ConstraintViolationException cause = (ConstraintViolationException)(e.getCause());
            message = cause.getSQLException().getMessage();
        } else {
            message = e.getMessage();
        }
    }

    public DataIntegrityExceptionMessage(StaleStateException e){
        this.messageType = "StaleState";
        this.message = e.getMessage();
    }
}
