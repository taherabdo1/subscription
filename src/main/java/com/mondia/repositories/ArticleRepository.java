package com.mondia.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.mondia.entities.Article;

@Transactional
public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
