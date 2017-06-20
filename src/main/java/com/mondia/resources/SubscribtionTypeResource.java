package com.mondia.resources;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.entities.SubscribtionType;
import com.mondia.repositories.SubscribtionTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api
@RestController
@RequestMapping("/subscribtionType")
public class SubscribtionTypeResource {

	@Autowired
	private SubscribtionTypeRepository subscribtionTypeRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public List<SubscribtionType> getAllSubscribtionTypes() throws Exception{
		List<SubscribtionType> packages = (List<SubscribtionType>) subscribtionTypeRepository.findAll();
		if(packages.size()<= 1){
			throw new Exception("there is no packages to return");
		}
		return packages;
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/get/{subscribtionTypeId}")
	public SubscribtionType getSubscribtionTypeById(@PathParam("subscribtionTypeId")Integer subscribtionTypeId) throws Exception{
		return subscribtionTypeRepository.findOne(subscribtionTypeId);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getByContingentType")
	public List<SubscribtionType> getSubscribtionTypeById(@ApiParam(value="a list of values", allowableValues="game , music , app",allowMultiple= true , required=false) @RequestParam("contingentTypeList")List<String> contingentTypeList) throws Exception{
		return subscribtionTypeRepository.findDistinctByContingentsTypeIn(contingentTypeList);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public SubscribtionType addNewSubscribtionType(@RequestBody SubscribtionType subscribtionType){
		return subscribtionTypeRepository.save(subscribtionType);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public boolean deleteSubscribtionType(@RequestBody SubscribtionType subscribtionType) throws Exception{
		try{
			subscribtionTypeRepository.delete(subscribtionType);			
		}catch (Exception e) {
			throw new Exception("you should delete the related Contingents first");
		}
		return true;
	}

	
}
