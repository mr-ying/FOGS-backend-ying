package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Supplier;
import FinTechOne.FOGS.domain.QSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestResource(path = "Supplier")
public interface SupplierRepository extends JpaRepository<Supplier, Long>,
        QueryDslPredicateExecutor<Supplier>,
        QuerydslBinderCustomizer<QSupplier> {
    Supplier findByCode(@Param("code") String code);
    Long countByCode(String code);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Supplier> S save(@RequestBody S supplier);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Supplier supplier);

    @Override
    default public void customize(QuerydslBindings bindings, QSupplier supplier){
        bindings.bind(supplier.code).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(supplier.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(supplier.type).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(supplier.address).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(supplier.email).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(supplier.contactNum).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
