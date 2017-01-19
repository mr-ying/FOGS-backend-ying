package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Material;
import FinTechOne.FOGS.repository.MaterialRepository;
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
public class MaterialRestEventHandler {
    @Autowired
    private MaterialRepository repo;
    @HandleBeforeCreate
    public void checkDupKey(Material material) throws Exception{

        if (material.getCode() != null &&
                material.getCode().trim() != "" &&
                repo.countByCode(material.getCode())>0) {
            List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
            keyProperties.add(KeyProperty.of("code", material.getCode()));
            throw new DuplicateKeyException("Material", keyProperties, "DuplicateKey", "Record already exists in Material");
        }
    }
}
