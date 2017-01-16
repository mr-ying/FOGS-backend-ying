package FinTechOne.FOGS.configuration.validator;

import FinTechOne.FOGS.validator.EntitlementValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class EntitlementValidatorConfiguration extends RepositoryRestConfigurerAdapter {
    @Override public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
        v.addValidator("beforeSave", new EntitlementValidator());
        v.addValidator("beforeCreate", new EntitlementValidator());
    }

}
