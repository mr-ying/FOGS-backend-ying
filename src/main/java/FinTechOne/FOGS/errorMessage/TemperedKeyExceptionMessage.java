package FinTechOne.FOGS.errorMessage;

import FinTechOne.FOGS.exception.TemperedKeyException;
import FinTechOne.FOGS.exception.TemperedKeyException.KeyProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.List;

/**
 * Created by User on 14/1/2017.
 */
public class TemperedKeyExceptionMessage {
    @JsonProperty("messageType")
    private String messageType;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("key")
    private List<KeyProperty> keyProperties;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

    public TemperedKeyExceptionMessage(TemperedKeyException e, MessageSource messageSource){
        this.messageType = "TemperedKey";
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
