package FinTechOne.FOGS.domain.projection;

import FinTechOne.FOGS.domain.SalesContractColorSize;
import FinTechOne.FOGS.domain.SalesContractDetail;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name="SalesContractDetailInline", types={SalesContractDetail.class})
public interface SalesContractDetailInline {
    int getSeqNum();
    String getPoNum();
    Set<SalesContractColorSize> getSalesContractColorSizes();
}
