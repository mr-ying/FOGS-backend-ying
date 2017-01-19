package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Supplier;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.exception.DuplicateKeyException.KeyProperty;
import FinTechOne.FOGS.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RepositoryEventHandler
public class SupplierRestEventHandler {
    @Autowired
    private SupplierRepository repo;
    @HandleBeforeCreate
    public void checkDupKey(Supplier supplier) throws Exception{

        if (supplier.getCode() != null &&
                supplier.getCode().trim() != "" &&
                repo.countByCode(supplier.getCode())>0) {
            List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
            keyProperties.add(KeyProperty.of("code", supplier.getCode()));
            throw new DuplicateKeyException("Supplier", keyProperties, "DuplicateKey", "Record already exists in Supplier");
        }
    }
}
