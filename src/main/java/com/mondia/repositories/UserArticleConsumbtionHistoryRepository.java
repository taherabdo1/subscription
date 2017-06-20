package com.mondia.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.mondia.entities.UserArticleConsumbtionHistory;

@Transactional
public interface UserArticleConsumbtionHistoryRepository extends CrudRepository<UserArticleConsumbtionHistory, Integer> {

}
