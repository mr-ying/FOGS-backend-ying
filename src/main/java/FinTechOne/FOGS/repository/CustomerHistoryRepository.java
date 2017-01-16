package FinTechOne.FOGS.repository;

import FinTechOne.FOGS.domain.CustomerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerHistoryRepository extends JpaRepository<CustomerHistory, Long> {
}
