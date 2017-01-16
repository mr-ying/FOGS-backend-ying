package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.repositoryCustomInterface.SalesContractCustom;
import FinTechOne.FOGS.domain.SalesContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesContractRepository  extends JpaRepository<SalesContract, Long>, SalesContractCustom {
    @Query(value = "select * from SalesContract s union select * from SalesContract_tx t ", nativeQuery = true)
    List<SalesContract> findAll();
}
