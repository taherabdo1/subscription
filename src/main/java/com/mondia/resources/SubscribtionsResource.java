package com.mondia.resources;

import java.util.Date;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.entities.Subscribtion;
import com.mondia.entities.SubscribtionType;
import com.mondia.entities.User;
import com.mondia.repositories.SubscribtionRepository;
import com.mondia.repositories.SubscribtionTypeRepository;
import com.mondia.repositories.UserRepository;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/subscribtion")
public class SubscribtionsResource {

	@Autowired
	SubscribtionTypeRepository subscribtionTypeRepository;

	@Autowired
	SubscribtionRepository subscribtionRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String testMethod() {
		return "test succeeded";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subscribe", consumes = "application/json")
	public Subscribtion subscribe( @RequestParam Integer userId ,@RequestParam Integer subscribtionTypeId) {
		System.out.println("user id: " + userId +" and subscribtion type id: " + subscribtionTypeId);
		Subscribtion subscribtion = new Subscribtion();
		subscribtion.setStartDate(new Date());

		SubscribtionType subscribtionType = new SubscribtionType();
		subscribtionType.setId(subscribtionTypeId);
		subscribtion.setSubscribtionType(subscribtionType);
		
		User user = new User();
		user.setId(userId);
		subscribtion.setUser(user);
		
		return subscribtionRepository.save(subscribtion);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public List<Subscribtion> getAllSubscribtions() throws Exception {
		List<Subscribtion> packages = (List<Subscribtion>) subscribtionRepository.findAll();
		if (packages.size() < 1) {
			throw new Exception("there is no packages to return");
		}
		return packages;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public Subscribtion addNewSubscribtion(@RequestBody Subscribtion subscribtion) {
		User persistedUser = userRepository.findByEmail(subscribtion.getUser().getEmail());
		System.out.println("user id is : " + persistedUser.getId() + " *******************************");
		subscribtion.setUser(persistedUser);
		return subscribtionRepository.save(subscribtion);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public boolean deleteSubscribtion(@RequestBody Subscribtion subscribtion) throws Exception {
		subscribtionRepository.delete(subscribtion);
		return true;
	}

}
