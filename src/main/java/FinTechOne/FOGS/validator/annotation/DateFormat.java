package FinTechOne.FOGS.validator.annotation;


import FinTechOne.FOGS.validator.DateFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface DateFormat {
    String message() default "{FinTechOne.FOGS.validator.annotation.DateFormat.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value();
}
