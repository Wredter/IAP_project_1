package com.IAP.car_exchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.IAP.car_exchange.Model.Responses;


public interface ResponsesRepository extends JpaRepository<Responses, Long> {
	
	@Query("SELECT r FROM Responses r WHERE r.sent != true")
	List<Responses> getAllUnsyc();

}
