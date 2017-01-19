package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Currency;
import FinTechOne.FOGS.domain.QCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestResource(path = "Currency")
public interface CurrencyRepository extends JpaRepository<Currency, Long> ,
        QueryDslPredicateExecutor<Currency>,
        QuerydslBinderCustomizer<QCurrency> {

    Currency findByCurrency(@Param("currency") String currency);
    Long countByCurrency(String currency);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    <S extends Currency> S save(@RequestBody S currency);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_APPROVER')")
    void delete(@RequestBody Currency currency);

    @Override
    default public void customize(QuerydslBindings bindings, QCurrency currency){
        bindings.bind(currency.currency).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
        bindings.bind(currency.name).first((path, value) -> path.like(RepositoryUtilities.formLikeValue(value)));
    }
}
