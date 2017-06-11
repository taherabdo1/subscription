package com.mondia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.entities.Article;
import com.mondia.repositories.ArticleRepository;

@RestController
@RequestMapping("/article")
public class ArticleResource {

	@Autowired
	ArticleRepository articleRepository;
	
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
	
	/**
	 * 
	 * @param article
	 * @return the new created article object
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public Article addNewArticle(@RequestBody Article article){
		return articleRepository.save(article);
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
