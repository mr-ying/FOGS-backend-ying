package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Factory;
import FinTechOne.FOGS.repository.FactoryRepository;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.DuplicateKeyException.KeyProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18/1/2017.
 */
@Component
@RepositoryEventHandler
public class FactoryRestEventHandler {
    @Autowired
    private FactoryRepository repo;
    @HandleBeforeCreate
    public void checkDupKey(Factory factory) throws Exception{

        if (factory.getCode() != null &&
                factory.getCode().trim() != "" &&
                repo.countByCode(factory.getCode())>0) {
            List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
            keyProperties.add(KeyProperty.of("code", factory.getCode()));
            throw new DuplicateKeyException("Factory", keyProperties, "DuplicateKey", "Record already exists in Factory");
        }
    }
}
