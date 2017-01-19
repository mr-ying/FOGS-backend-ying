package FinTechOne.FOGS.validator;

import FinTechOne.FOGS.domain.EntityBase;
import FinTechOne.FOGS.repository.EntitlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.util.Set;

@Service
public class EntityValidator implements Validator {
    @Autowired
    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return EntityBase.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EntityBase p = (EntityBase)target;
        Set<ConstraintViolation<EntityBase>> violations = validator.validate(p, Default.class);
        for (ConstraintViolation<EntityBase> constraintViolation : violations) {
            errors.rejectValue(constraintViolation.getPropertyPath().toString(), constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(),constraintViolation.getMessage());
        }
    }
}
