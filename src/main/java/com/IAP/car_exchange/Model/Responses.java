package com.IAP.car_exchange.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "responses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Responses {
	
	@Getter
	@Setter
	@Id
	@NotNull
	@Column(name="id")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Getter
	@Setter
	@Column(name="worker_id",nullable=true)
	public Long workerId;
	
	@Getter
	@Setter
	@Column(name="plate_number",nullable=true)
    String plateNumber;
	
	@Getter
	@Setter
	@Column(name="license_number",nullable=true)
    String licenseNumber;
	
	@Getter
	@Setter
	@Column(name="model",nullable=true)
    String model;
	
	@Getter
	@Setter
	@Column(name="type",nullable=true)
    String type;

	@Getter
	@Setter
	@Column(name="vin_number",nullable=true)
    String vinNumber;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="request_id")
    Long requestId;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="request_status")
    String requestStatus;
	
	@Getter
	@Setter
	@Column(name="approved_by",nullable=true)
    String approvedBy;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="approved_date")
    Date approvedDate;
	
	@Getter
	@Setter
	@Column(name="sent",nullable=true)
    Boolean sent;

}
