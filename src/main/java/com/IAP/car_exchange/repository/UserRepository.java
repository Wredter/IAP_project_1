package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT COUNT(u) = 1 FROM User u WHERE u.id = ?1")
	Boolean getUserWithId(Long id);
}
