package com.IAP.car_exchange.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.IAP.car_exchange.Controller.DataHolders.RequestData;
import com.IAP.car_exchange.Model.Request;
import com.IAP.car_exchange.repository.Querries;

@RestController
public class RequestController {
	@Autowired
	Querries DataAccess;
	
	@PostMapping("request")
    public @ResponseBody
    ResponseEntity<String> createRequest(@RequestBody RequestData dataHolder){
		
        Request request = DataAccess.addRequest(
        		dataHolder.getRequestorId(),
        		dataHolder.getBranchId(),
        		dataHolder.getCarModel(),
        		dataHolder.getVehiclePreffered(),
        		//dataHolder.getRequestDate()
        		new Date()
        		);
        
        return ResponseEntity.ok(request.toString());
    }
	
	@GetMapping("requests")
	public @ResponseBody Iterable<Request> getRequests(){
		return DataAccess.getAllRequests();
	}
	

}
