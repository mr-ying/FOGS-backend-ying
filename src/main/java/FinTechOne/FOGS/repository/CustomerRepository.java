package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Customer;
import FinTechOne.FOGS.domain.QCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestResource(path = "Customer")
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        QueryDslPredicateExecutor<Customer>,
        QuerydslBinderCustomizer<QCustomer> {

    Customer findByCode(@Param("code") String code);
    Long countByCode(String code);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Customer> S save(@RequestBody S customer);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Customer customer);

    @Override
    default public void customize(QuerydslBindings bindings, QCustomer customer){
        bindings.bind(customer.code).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(customer.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(customer.address).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(customer.brand).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
