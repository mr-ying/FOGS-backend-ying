package FinTechOne.FOGS.validator.hibernate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyCodeValidator.class)
@Documented
public @interface CurrencyCode {
    String message() default "{FinTechOne.FOGS.validator.hibernate.CurrencyCode.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

//    String value();
}
