package com.IAP.car_exchange.Controller.DataHolders;

import java.util.Date;

import lombok.Data;

@Data
public class RequestData {
	Long requestId;
	Long requestorId;
	Long branchId;
	String carModel;
	String vehiclePreffered;
	Date requestDate;
	String requestStatus;
	String approvedBy;
	Date approvedDate;

}
