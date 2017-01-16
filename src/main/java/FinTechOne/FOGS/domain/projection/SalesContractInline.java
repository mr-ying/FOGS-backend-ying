package FinTechOne.FOGS.domain.projection;

import FinTechOne.FOGS.domain.SalesContract;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name="SalesContractInline", types={SalesContract.class})
public interface SalesContractInline {
    String getScNum();
    String getCustomerId();
    String getCreatedBy();
    Date getCreatedDate();
    String getLastModifiedBy();
    Date getLastModifiedDate();
    Set<SalesContractDetailInline> getSalesContractDetails();
}
