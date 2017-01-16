package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Entitlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Entitlement")
public interface EntitlementRepository extends JpaRepository<Entitlement, Long> {
    Entitlement findByUserId(@Param("userId") String userId);
    Long countByUserId(String userId);
}
