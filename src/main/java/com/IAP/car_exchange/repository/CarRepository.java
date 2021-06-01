package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Model.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, String> {
	@Query("SELECT c FROM Car c WHERE c.model=?1 AND c.type=?2 AND c.assigned= false")
	Iterable<Car> findByModelType(String model,String type);
}
