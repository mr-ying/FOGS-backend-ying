package FinTechOne.FOGS.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(code= HttpStatus.CONFLICT, reason = "Duplicate Key")
@AllArgsConstructor
@Getter
public class DuplicateKeyException extends Exception {
    private String entityName;
//    private String keyProperty;
//    private String keyValue;
    private List<KeyProperty> keyProperties;
    private String code;
    private String message;

    @Value(staticConstructor = "of")
    public static class KeyProperty {
        String property;
        Object value;
    }
}
