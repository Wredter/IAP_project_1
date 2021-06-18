package com.IAP.car_exchange;

public class SynchronizationConfiguration {
	
	// connection to BO
	public static final String uriToBo = "http://localhost:8826/details";
	
	// connection from BO
	public static final String  uriFromBo = "request";
	
	// delay sync timer in millisecond
	public static final int delay = 5000;

}
