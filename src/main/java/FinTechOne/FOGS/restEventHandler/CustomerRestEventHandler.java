package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Customer;
import FinTechOne.FOGS.repository.CustomerRepository;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.DuplicateKeyException.KeyProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RepositoryEventHandler
public class CustomerRestEventHandler {
    @Autowired
    private CustomerRepository repo;
    @HandleBeforeCreate
    public void checkDupKey(Customer customer) throws Exception{

        if (customer.getCode() != null &&
                customer.getCode().trim() != "" &&
                repo.countByCode(customer.getCode())>0) {
            List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
            keyProperties.add(KeyProperty.of("code", customer.getCode()));
            throw new DuplicateKeyException("Customer", keyProperties, "DuplicateKey", "Record already exists in Customer");
        }
    }
}
