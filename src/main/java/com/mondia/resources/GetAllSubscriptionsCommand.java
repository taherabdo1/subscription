package com.mondia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetAllSubscriptionsCommand implements CommandMarker {
	
	@Autowired
	SubscribtionsResource  subscribtionsResource;
	
	@CliCommand(value = { "getAllSubs", "allSubs" }, help = "get all the subscribtions from back-end")
	public String getAllSubs() {
//		RestTemplate restTemplate = new RestTemplate();
//		String fooResourceUrl
//		  = "http://localhost:8080/subscribtion";
//		ResponseEntity<Subscrib> response
//		  = restTemplate.getForEntity(fooResourceUrl + "/getAll", String.class);
//		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		return "test shell";
	}
}
	 