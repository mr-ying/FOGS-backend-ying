package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Warehouse;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.DuplicateKeyException.KeyProperty;
import FinTechOne.FOGS.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RepositoryEventHandler
public class WarehouseRestEventHandler {
    @Autowired
    private WarehouseRepository repo;
    @HandleBeforeCreate
    public void checkDupKey(Warehouse warehouse) throws Exception{

        if (warehouse.getCode() != null &&
                warehouse.getCode().trim() != "" &&
                repo.countByCode(warehouse.getCode())>0) {
            List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
            keyProperties.add(KeyProperty.of("code", warehouse.getCode()));
            throw new DuplicateKeyException("Warehouse", keyProperties, "DuplicateKey", "Record already exists in Warehouse");
        }
    }
}
