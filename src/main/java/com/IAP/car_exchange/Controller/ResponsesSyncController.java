package com.IAP.car_exchange.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.IAP.car_exchange.SynchronizationConfiguration;
import com.IAP.car_exchange.repository.ResponsesSycService;

@RestController
public class ResponsesSyncController {
	@Autowired
	ResponsesSycService responsesSycService;
	
	private static final Logger logger = LoggerFactory.getLogger(ResponsesSyncController .class);
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	//@Scheduled(fixedDelay=SynchronizationConfiguration.delay)
	public void synchronizeResponses() {
		logger.info("Scheduled Synchronization Task :: Execution Time - {}",dateFormat.format(LocalDateTime.now()));
		try {
			ResponseEntity<String> sync = responsesSycService.tryToSync();
		} catch(Exception ex) {
			logger.error("We have run into an error: we will reconnect shortly");
		}
	}

}
