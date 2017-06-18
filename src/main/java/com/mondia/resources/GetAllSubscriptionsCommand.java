package com.mondia.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mondia.entities.Subscribtion;

@Component
public class GetAllSubscriptionsCommand implements CommandMarker {
	
	@Autowired
	SubscribtionsResource  subscribtionsResource;
	
	@CliCommand(value = { "getAllSubsIds", "allSubsIds" }, help = "get all the subscribtions from back-end")
	public List<Integer> getAllSubs() {
		List<Integer> result = new ArrayList<>();
		try {
			for(Subscribtion subscribtion : subscribtionsResource.getAllSubscribtions()){
				result.add(subscribtion.getId());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
	 