package FinTechOne.FOGS.repositoryImpl;

import FinTechOne.FOGS.repositoryCustomInterface.SalesContractCustom;
import FinTechOne.FOGS.domain.SalesContract;
import FinTechOne.FOGS.domain.SalesContract_Tx;
import FinTechOne.FOGS.repository.SalesContractRepository;
import FinTechOne.FOGS.repository.SalesContract_TxRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class SalesContractRepositoryImpl implements SalesContractCustom {
    @Autowired
    SalesContractRepository masterRepo;
    @Autowired
    SalesContract_TxRepository txRepo;

    @Transactional
    public SalesContract approve(SalesContract_Tx sctx){
        txRepo.delete(sctx);
        SalesContract sc = new SalesContract();
        sc.setScNum(sctx.getScNum());
        sc.setCustomerId(sctx.getCustomerId());
        masterRepo.save(sc);
        return sc;
    }
}
