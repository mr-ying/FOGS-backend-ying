package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.SalesContractView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "salesContracts", path = "SalesContract020", itemResourceRel = "salesContract")
public interface SalesContractViewRepository  extends JpaRepository<SalesContractView, Long> {
}
