package com.mondia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.entities.Contingent;
import com.mondia.entities.SubscribtionType;
import com.mondia.repositories.ContingentRepository;

@RestController
@RequestMapping("/continget")
public class ContingentResource {

	@Autowired
	ContingentRepository contingentRepository;
	
	
	/**
	 * 
	 * @return all contingents from the contingent table
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public List<Contingent> getArticles(){
		List<Contingent> contingents = (List<Contingent>) contingentRepository.findAll();
		return contingents;
	}
	
	/**
	 * 
	 * @param contingent
	 * @return the new created contingent object
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public Contingent addNewContingent(@RequestBody Contingent contingent){
		return contingentRepository.save(contingent);
	}

	/**
	 * 
	 * @param contingent object contains at least the contingent Id to be deleted
	 * @return boolean to inform if the process is done correctly or not
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public boolean deletecontingent(@RequestBody Contingent contingent) throws Exception{
		try{
			contingentRepository.delete(contingent);			
		}catch (Exception e) {
			throw new Exception("the contingent with this id can't be deleted");
		}
		return true;
	}

	/**
	 * 
	 * @param subscribtion type object
	 * @return list<contingent> of this subscribtionType from database
	 * @throws Exception
	 */
//	@RequestMapping(method = RequestMethod.GET, value = "/findBySubscribtionType")
//	public List<Contingent> getAllContingetsOfApackage(@RequestParam("subscribtionTypeId") Integer subscribtionTypeId){
//		List<Contingent> contingents = contingentRepository.findBySubscribtionType(subscribtionTypeId);
//		System.out.println("the contingent list size is : " + contingents.size() + " ---------------------------------------");
//		return contingents;
//	}
}
