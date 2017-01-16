package FinTechOne.FOGS.validator;


import FinTechOne.FOGS.validator.annotation.DateFormat;
import org.apache.commons.lang.time.FastDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.util.Date;

public class DateFormatValidator implements ConstraintValidator<DateFormat, Date> {

    private String format;

    public void initialize(DateFormat constraintAnnotation) {
        format = constraintAnnotation.value();
    }

    public boolean isValid(
            Date date,
            ConstraintValidatorContext constraintValidatorContext) {

        if ( date == null ) {
            return true;
        }

        FastDateFormat dateFormat = FastDateFormat.getInstance(format);
            date.toString();
            return true;
    }
}
