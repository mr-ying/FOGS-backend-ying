package FinTechOne.FOGS.restEventHandler;

import FinTechOne.FOGS.domain.Currency;
import FinTechOne.FOGS.exception.DuplicateKeyException;
import FinTechOne.FOGS.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RepositoryEventHandler
public class CurrencyRestEventHandler {
    @Autowired
    private CurrencyRepository repo;

    @HandleBeforeCreate
    public void checkDupKey(Currency currency) throws Exception{

        if (currency.getCurrency() != null &&
                currency.getCurrency().trim() != "" &&
                repo.countByCurrency(currency.getCurrency())>0) {
            List<DuplicateKeyException.KeyProperty> keyProperties = new ArrayList<DuplicateKeyException.KeyProperty>();
            keyProperties.add(DuplicateKeyException.KeyProperty.of("currency", currency.getCurrency()));
            throw new DuplicateKeyException("Currency", keyProperties, "DuplicateKey", "Record already exists in Currency");
        }
    }
}
