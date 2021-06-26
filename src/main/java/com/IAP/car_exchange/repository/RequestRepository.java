package com.IAP.car_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.IAP.car_exchange.Model.Request;

public interface RequestRepository extends JpaRepository<Request,Long> {
	@Query("SELECT r FROM Request r WHERE r.requestStatus=Null ORDER BY r.requestDate DESC")
	Iterable<Request> pendingRequests();
	
	@Query(value = "SELECT r.request_id FROM requests r WHERE r.request_status IS NULL ORDER BY r.request_date DESC LIMIT 1", nativeQuery = true)
	Long getOneRecord();
	

}
