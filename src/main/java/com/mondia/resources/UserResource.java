package com.mondia.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.entities.Article;
import com.mondia.entities.User;
import com.mondia.repositories.UserArticleConsumbtionHistoryRepository;
import com.mondia.repositories.UserRepository;
import com.mondia.entities.UserArticleConsumbtionHistory;

import scala.annotation.meta.setter;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserArticleConsumbtionHistoryRepository userArticleConsumbtionHistoryRepository;

	/**
	 * 
	 * @return all users for the user table
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public List<User> getAllUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		Article article = new Article();
		article.setId(2);
		User user = new User();
		user.setId(1);
		UserArticleConsumbtionHistory userHistory = new UserArticleConsumbtionHistory();
		userHistory.setUser(user);
		userHistory.setArticle(article);
		userHistory.setConsumbtionDate(new Date());
		userArticleConsumbtionHistoryRepository.save(userHistory);
		return users;
	}
	/**
	 * 
	 * @param user
	 * @return the new created user object
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public User addNewUser(@RequestBody User user){
		return userRepository.save(user);
	}

	/**
	 * 
	 * @param user object contains at least the userId to be deleted
	 * @return boolean to inform if the process is done correctly or not
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public boolean deleteUser(@RequestBody User user) throws Exception{
		try{
			userRepository.delete(user);			
		}catch (Exception e) {
			throw new Exception("the selected user can't be deleted");
		}
		return true;
	}

}
