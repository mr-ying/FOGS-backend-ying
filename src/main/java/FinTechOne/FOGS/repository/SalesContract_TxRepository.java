package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.SalesContract_Tx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesContract_TxRepository extends JpaRepository<SalesContract_Tx, Long> {
    SalesContract_Tx findByScNum(String scNum);
}
