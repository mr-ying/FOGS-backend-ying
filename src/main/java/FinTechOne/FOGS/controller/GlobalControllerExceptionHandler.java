package FinTechOne.FOGS.controller;

import FinTechOne.FOGS.errorMessage.*;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.TemperedKeyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.CallbackException;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionMessage> handleConstraintConflict(ConstraintViolationException e){
        return response(HttpStatus.BAD_REQUEST, new HttpHeaders(), new ValidationExceptionMessage(e, messageSource));
    }

    @ExceptionHandler(RepositoryConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionMessage> handleConstraintConflict(RepositoryConstraintViolationException e){
        return response(HttpStatus.BAD_REQUEST, new HttpHeaders(), new ValidationExceptionMessage(e, messageSource));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<FormatExceptionMessage> handleFormatConflict(InvalidFormatException e) {
        return response(HttpStatus.BAD_REQUEST, new HttpHeaders(), new FormatExceptionMessage(e));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DataIntegrityExceptionMessage> handleDataIntegrityConflict(DataIntegrityViolationException e) {
        return response(HttpStatus.CONFLICT, new HttpHeaders(), new DataIntegrityExceptionMessage(e));
    }

    @ExceptionHandler(StaleStateException.class)
    public ResponseEntity<DataIntegrityExceptionMessage> handleStaleState(StaleStateException e) {

        return response(HttpStatus.INTERNAL_SERVER_ERROR, new HttpHeaders(), new DataIntegrityExceptionMessage(e));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<DatabaseExceptionMessage> handleDataIntegrityConflict(DuplicateKeyException e) {
        return response(HttpStatus.CONFLICT, new HttpHeaders(), new DatabaseExceptionMessage(e, messageSource));
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<TemperedKeyExceptionMessage> handleDataIntegrityConflict(JpaSystemException e) throws JpaSystemException {
        if (CallbackException.class.isAssignableFrom(e.getCause().getClass()) && TemperedKeyException.class.isAssignableFrom(e.getCause().getCause().getClass()) ){
            return response(HttpStatus.BAD_REQUEST, new HttpHeaders(), new TemperedKeyExceptionMessage((TemperedKeyException) e.getCause().getCause(), messageSource));
        }
        throw e;
    }

    private static <T> ResponseEntity<T> response(HttpStatus status, HttpHeaders headers, T body) {

        return new ResponseEntity<T>(body, headers, status);
    }

}
