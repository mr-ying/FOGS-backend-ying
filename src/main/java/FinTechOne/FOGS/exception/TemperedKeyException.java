package FinTechOne.FOGS.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by User on 14/1/2017.
 */
//@ResponseStatus(code= HttpStatus.CONFLICT, reason = "Key Tempered")
@AllArgsConstructor
@Getter
public class TemperedKeyException extends Exception {
    private String entityName;
    private List<KeyProperty> keyProperties;
    private String code;
    private String message;

    @Value(staticConstructor = "of")
    public static class KeyProperty {
        String property;
        Object oldValue;
        Object newValue;
    }

}
