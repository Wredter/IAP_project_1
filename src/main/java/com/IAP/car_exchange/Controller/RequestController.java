package com.IAP.car_exchange.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.IAP.car_exchange.Controller.DataHolders.RequestData;
import com.IAP.car_exchange.Model.Office;
import com.IAP.car_exchange.Model.Request;
import com.IAP.car_exchange.repository.Querries;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
        		new Date(),
        		dataHolder.getRequestId()
        		);
        
        //Send this Request to Main Office as well
        //String uri = "http://localhost:8081/request";
        //RestTemplate restTemplate = new RestTemplate();
        //RequestData result = restTemplate.postForObject(uri,dataHolder, RequestData.class);

        return ResponseEntity.ok(null);
    }
	
	@GetMapping("requests")
	public @ResponseBody Iterable<Request> getRequests(){
		return DataAccess.getAllRequests();
	}
	
    @GetMapping("request/{id}")
    public Request getRequest(@PathVariable Long id){
        return DataAccess.getRequestById(id);
    }
    
    @DeleteMapping("_request/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id){
        DataAccess.deleteRequest(id);
        return ResponseEntity.ok("Removed");
    }

	

}
