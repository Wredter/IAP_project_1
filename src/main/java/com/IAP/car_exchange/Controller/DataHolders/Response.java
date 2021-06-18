package com.IAP.car_exchange.Controller.DataHolders;

import java.util.Date;

import lombok.Data;

@Data
public class Response {
	Long id;
	Long workerId;
    String plateNumber;
    String licenseNumber;
    String model;
    String type;
    String vinNumber;
    Long requestId;
    String requestStatus;
    String approvedBy;
    Date approvedDate;
    Boolean sent;
}
