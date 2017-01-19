package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Entitlement;
import FinTechOne.FOGS.domain.QEntitlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestResource(path = "Entitlement")
public interface EntitlementRepository extends JpaRepository<Entitlement, Long>,
        QueryDslPredicateExecutor<Entitlement>,
        QuerydslBinderCustomizer<QEntitlement>{

    Entitlement findByUserId(@Param("userId") String userId);
    Long countByUserId(String userId);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Entitlement> S save(@RequestBody S entitlement);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Entitlement entitlement);

    @Override
    default public void customize(QuerydslBindings bindings, QEntitlement entitlement){
        bindings.bind(entitlement.userId).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(entitlement.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(entitlement.jobTitle).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(entitlement.role).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
