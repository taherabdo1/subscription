package com.mondia.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.mondia.entities.Article;
import com.mondia.entities.Subscribtion;

@Transactional
public interface SubscribtionRepository extends CrudRepository<Subscribtion, Integer> {

}
