package FinTechOne.FOGS.repositoryCustomInterface;

import FinTechOne.FOGS.domain.SalesContract;
import FinTechOne.FOGS.domain.SalesContract_Tx;

public interface SalesContractCustom {
    SalesContract approve (SalesContract_Tx sctx);
}
