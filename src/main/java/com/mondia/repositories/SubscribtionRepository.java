package com.mondia.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mondia.entities.Article;
import com.mondia.entities.Subscribtion;

public interface SubscribtionRepository extends CrudRepository<Subscribtion, Integer> {

}
