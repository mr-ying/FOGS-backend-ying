package FinTechOne.FOGS.errorMessage;

import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.TemperedKeyException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.List;

@Getter
public class DatabaseExceptionMessage {

    @JsonProperty("messageType")
    private String messageType;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("key")
    private List<DuplicateKeyException.KeyProperty> keyProperties;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

    public DatabaseExceptionMessage(DuplicateKeyException e, MessageSource messageSource){
        this.messageType = "DuplicateKey";
        this.entity = e.getEntityName();
        this.keyProperties = e.getKeyProperties();
        this.code = e.getCode();
        try {
            this.message = messageSource.getMessage(this.code, null, null);
        } catch (NoSuchMessageException noSuchMessageException){
            this.message = e.getMessage();
        }
    }
}
