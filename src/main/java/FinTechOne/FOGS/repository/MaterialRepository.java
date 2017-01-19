package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Material;
import FinTechOne.FOGS.domain.QMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestResource(path = "Material")
public interface MaterialRepository extends JpaRepository<Material, Long>,
        QueryDslPredicateExecutor<Material>,
        QuerydslBinderCustomizer<QMaterial> {
    Material findByCode(@Param("code") String code);
    Long countByCode(String code);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Material> S save(@RequestBody S material);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Material material);

    @Override
    default public void customize(QuerydslBindings bindings, QMaterial material){
        bindings.bind(material.code).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(material.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(material.type).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(material.description).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
