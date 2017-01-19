package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.QWarehouse;
import FinTechOne.FOGS.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestResource(path = "Warehouse")
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>,
        QueryDslPredicateExecutor<Warehouse>,
        QuerydslBinderCustomizer<QWarehouse> {
    Warehouse findByCode(@Param("code") String code);
    Long countByCode(String code);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Warehouse> S save(@RequestBody S warehouse);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Warehouse warehouse);

    @Override
    default public void customize(QuerydslBindings bindings, QWarehouse warehouse){
        bindings.bind(warehouse.code).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(warehouse.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(warehouse.address).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
