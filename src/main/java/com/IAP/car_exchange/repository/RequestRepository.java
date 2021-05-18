package com.IAP.car_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IAP.car_exchange.Model.Request;

public interface RequestRepository extends JpaRepository<Request,Long> {

}
