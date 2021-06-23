package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OfficeRepository extends JpaRepository<Office, Long> {
	@Query("SELECT COUNT(o) = 1 FROM Office o WHERE o.id = ?1")
	Boolean officeExists(Long id);
}
