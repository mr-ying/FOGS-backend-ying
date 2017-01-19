package FinTechOne.FOGS.validator.hibernate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    public void initialize(PhoneNumber constraintAnnotation) {
    }
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {

        phoneNumber = phoneNumber.trim();
        if ( phoneNumber == null || phoneNumber == "") {
            return true;
        }
        Pattern pattern = Pattern.compile("\\A\\+?[0-9 -]+\\z");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.find();
    }

}
