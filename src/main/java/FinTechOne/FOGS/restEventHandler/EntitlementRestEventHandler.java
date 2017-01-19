package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Currency;
import FinTechOne.FOGS.domain.Entitlement;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.DuplicateKeyException.KeyProperty;
import FinTechOne.FOGS.repository.CurrencyRepository;
import FinTechOne.FOGS.repository.EntitlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RepositoryEventHandler
public class EntitlementRestEventHandler {
    @Autowired
    private MailSender mailSender;

    @Value("${FOGS.send.password.thru.email}")
    private boolean sendPasswordThruEmail;

    @Autowired
    private EntitlementRepository repo;

    @HandleBeforeCreate
    public void genPassword(Entitlement entitlement) throws Exception{

        if (entitlement.getUserId() != null &&
                entitlement.getUserId().trim() != "" &&
                repo.countByUserId(entitlement.getUserId())>0) {
            List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
            keyProperties.add(KeyProperty.of("userId", entitlement.getUserId()));
            throw new DuplicateKeyException("Entitlement", keyProperties, "DuplicateKey", "Record already exists in Entitlement");
        }

//        Generate password (temporarily the same as UserId)
        entitlement.setPassword(entitlement.getUserId());

    }

    @HandleAfterCreate
    public void sendEmail(Entitlement entitlement){
        if (sendPasswordThruEmail) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("New User created for you");
            mailMessage.setFrom("me@yingying.com");
            mailMessage.setTo("you@receiver.com");
            mailMessage.setText("your password is : " + entitlement.getPlainPassword());
            mailSender.send(mailMessage);
        }
    }
}
