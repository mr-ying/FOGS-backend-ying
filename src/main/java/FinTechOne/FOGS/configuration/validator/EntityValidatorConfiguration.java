package FinTechOne.FOGS.configuration.validator;

import FinTechOne.FOGS.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import javax.validation.Validator;

@Configuration
public class EntityValidatorConfiguration extends RepositoryRestConfigurerAdapter {

    @Autowired
    private EntityValidator entityValidator;

    @Override public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
        v.addValidator("beforeSave", entityValidator);
        v.addValidator("beforeCreate", entityValidator);
    }

}
