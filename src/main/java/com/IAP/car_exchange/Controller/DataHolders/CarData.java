package com.IAP.car_exchange.Controller.DataHolders;

import lombok.Data;

@Data
public class CarData {
	Long workerId;
    String plateNumber;
    String licenseNumber;
    String model;
    String type;
    String vinNumber;
    Boolean assigned;
}
