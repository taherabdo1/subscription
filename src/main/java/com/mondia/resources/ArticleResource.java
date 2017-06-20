package com.mondia.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.entities.Article;
import com.mondia.entities.ConsumedContingent;
import com.mondia.entities.Contingent;
import com.mondia.entities.User;
import com.mondia.entities.UserArticleConsumbtionHistory;
import com.mondia.repositories.ArticleRepository;
import com.mondia.repositories.ConsumedContingentRepository;
import com.mondia.repositories.ContingentRepository;
import com.mondia.repositories.UserArticleConsumbtionHistoryRepository;

@RestController
@RequestMapping("/article")
public class ArticleResource {

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ContingentRepository contingentRepository;
	@Autowired
	ConsumedContingentRepository consumedContingentRepository;
	@Autowired
	UserArticleConsumbtionHistoryRepository userArticleConsumbtionHistoryRepository;
	
	/**
	 * 
	 * @return all articles for the article table
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public List<Article> getArticles(){
		List<Article> articles = (List<Article>) articleRepository.findAll();
		return articles;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get/{articleId}")
	public Article getArticleById(@PathVariable("articleId") @NotNull Integer articleId){
		Article article =  articleRepository.findOne(articleId);
		return article;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/consume/{userId}/{articleId}")
	public Article consumeArticle(@PathVariable("articleId") @NotNull Integer articleId ,@PathVariable("userId")  Integer userId) throws Exception{
		Article article = articleRepository.findOne(articleId);
		//get all available contingents for this user
		List<ConsumedContingent> availbleContingents = consumedContingentRepository.findDistinctByTypeAndSubscribtionUserIdAndAmountGreaterThan(article.getType() , userId, 0);
		if(availbleContingents.size()>0){
			//add new history record
			UserArticleConsumbtionHistory historyRecord = new UserArticleConsumbtionHistory();
			historyRecord.setArticle(article);
			historyRecord.setConsumbtionDate(new Date());
			User user = new User();
			user.setId(userId);
			historyRecord.setUser(user);
			userArticleConsumbtionHistoryRepository.save(historyRecord);
			//decrement the available contingents for the user
			ConsumedContingent updatedConsumedcontingent = availbleContingents.get(0);
			consumedContingentRepository.updateAfterConsumingArticle(updatedConsumedcontingent.getId() , updatedConsumedcontingent.getAmount()-1);
			return article;
			
		}else{
			throw new Exception("you don't have permission to see this article");
		}
	}
	
	/**
	 * 
	 * @param article
	 * @return the new created article object
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public Article addNewArticle(@RequestBody Article article){
		Article persistedArticle = articleRepository.save(article);
		return persistedArticle;
	}

	/**
	 * 
	 * @param article object contains at least the articleId to be deleted
	 * @return boolean to inform if the process is done correctly or not
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete")
	public boolean deleteArticle(@RequestBody Article article) throws Exception{
		try{
			articleRepository.delete(article);			
		}catch (Exception e) {
			throw new Exception("the article with this id can't be deleted");
		}
		return true;
	}

}
