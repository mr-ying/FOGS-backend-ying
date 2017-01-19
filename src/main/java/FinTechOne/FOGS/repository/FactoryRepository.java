package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Factory;
import FinTechOne.FOGS.domain.QFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by User on 17/1/2017.
 */
@RepositoryRestResource(path = "Factory")
public interface FactoryRepository extends JpaRepository<Factory, Long>,
        QueryDslPredicateExecutor<Factory>,
        QuerydslBinderCustomizer<QFactory> {
    Factory findByCode(@Param("code") String code);
    Long countByCode(String code);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Factory> S save(@RequestBody S factory);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Factory factory);
    @Override
    default public void customize(QuerydslBindings bindings, QFactory factory){
        bindings.bind(factory.code).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(factory.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(factory.address).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(factory.email).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(factory.contactNum).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
