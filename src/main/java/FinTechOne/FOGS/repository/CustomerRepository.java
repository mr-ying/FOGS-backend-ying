package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
