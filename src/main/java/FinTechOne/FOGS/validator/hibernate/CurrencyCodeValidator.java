package FinTechOne.FOGS.validator.hibernate;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {
    public void initialize(CurrencyCode constraintAnnotation) {
    }
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {

        if ( currency == null ) {
            return true;
        }
        Pattern pattern = Pattern.compile("\\A[A-Z]{3}\\z");
        Matcher matcher = pattern.matcher(currency);

        return matcher.find();
    }
}
