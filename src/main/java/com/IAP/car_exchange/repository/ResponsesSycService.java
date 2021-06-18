package com.IAP.car_exchange.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.Controller.DataHolders.Response;
import com.IAP.car_exchange.Model.Responses;

import lombok.Data;

@Repository
@Data
public class ResponsesSycService {
	
	final ResponsesRepository responsesRepository;
	
	Response response = new Response();
	
	public ResponsesSycService(ResponsesRepository responsesRepository) {
		this.responsesRepository = responsesRepository;
	}
	
	public List<Responses> getAllUnsycedResponses(){
		
		return responsesRepository.getAllUnsyc();
	}
	
	
	public ResponseEntity<String> tryToSync(){
		
		List<Responses> responses = getAllUnsycedResponses();
		if (responses.isEmpty() == false) {
			for(Responses r:responses) {
				
				// Prepare the data to be sent to HQ for this request
				response.setWorkerId(r.getWorkerId());
				response.setPlateNumber(r.getPlateNumber());
				response.setLicenseNumber(r.getLicenseNumber());
				response.setModel(r.getModel());
				response.setType(r.getType());
				response.setVinNumber(r.getVinNumber());
				response.setRequestId(r.getRequestId());
				response.setRequestStatus(r.getRequestStatus());
				response.setApprovedBy(r.getApprovedBy());
				response.setApprovedDate(r.getApprovedDate());
				
				// Now send it
				//String uri = syncData.uriToBo;
				RestTemplate restTemplate = new RestTemplate();
				try {
					Response result = restTemplate.postForObject(SynchronizationConfiguration.uriToBo,response, Response.class);
				} catch(Exception e) {	
					System.out.println("We are sorry something happened we will try again later! "+e);
					//e.printStackTrace();
					throw e;	
				}
				
				// If success, change the sync status
				r.setSent(true);
				responsesRepository.save(r);
			}
		}

		return ResponseEntity.ok(null);
	}

}
