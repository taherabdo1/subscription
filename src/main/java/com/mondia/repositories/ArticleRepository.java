package com.mondia.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mondia.entities.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
