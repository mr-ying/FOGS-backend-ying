package FinTechOne.FOGS.validator;

import FinTechOne.FOGS.domain.Entitlement;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EntitlementValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Entitlement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Entitlement p = (Entitlement) target;
        //validate userId
        if (p.getUserId() == null)
            errors.rejectValue("userId", "NotNull", "may not be null");
        else
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "NotBlank");
        //validate name
        if (p.getName() == null)
            errors.rejectValue("name", "NotNull");
        else
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotBlank");
        //validate jobTitle
        if (p.getJobTitle() == null)
            errors.rejectValue("jobTitle", "NotNull");
        else
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobTitle", "NotBlank");
        //validate jobTitle
        if (p.getRole() == null)
            errors.rejectValue("role", "NotNull");
        else if (p.getRole().trim() == "")
            errors.rejectValue("role", "NotBlank");
        else if (!p.getRole().toLowerCase().equals("manager") &&
                !p.getRole().toLowerCase().equals("operater") &&
                !p.getRole().toLowerCase().equals("approver"))
            errors.rejectValue("role", "InvalidRole");
        //validate email
        if (p.getEmail() == null)
            errors.rejectValue("email", "NotNull");
        else if (p.getEmail().trim() == "")
            errors.rejectValue("email", "NotBlank");
        else if (!ValidationUtilities.validateEmail(p.getEmail()))
            errors.rejectValue("email", "Email");
  }

}
