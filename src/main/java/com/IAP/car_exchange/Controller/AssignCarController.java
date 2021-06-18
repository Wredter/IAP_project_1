package com.IAP.car_exchange.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.IAP.car_exchange.Controller.DataHolders.Response;
import com.IAP.car_exchange.Model.Car;
import com.IAP.car_exchange.Model.Request;
import com.IAP.car_exchange.repository.Querries;

@RestController
public class AssignCarController {

	@Autowired
	Querries DataAccess;
	
	@GetMapping("pendingrequests")
	public @ResponseBody Iterable<Request> getPendingRequest(){
		return DataAccess.getPendingRequests();
	}
	
	@GetMapping("filter/{model}/{type}")
	public @ResponseBody Iterable<Car> getCarByFilter(@PathVariable String model, @PathVariable String type) {
		return DataAccess.getCarByModelType(model, type);
	}
	
	@PostMapping("assign/{requestId}")
		public @ResponseBody 
		ResponseEntity<String> assign(@PathVariable Long requestId){
		Response response = DataAccess.assign(requestId);
		//System.out.println("passed here "+response);
		
		/*
		 * RestTemplate rest = new RestTemplate(); String uri =
		 * "http://localhost:8081/details"; Response result = rest.postForObject(uri,
		 * response, Response.class);
		 */
		
		return ResponseEntity.ok(null);
		}

	
	

	

}
