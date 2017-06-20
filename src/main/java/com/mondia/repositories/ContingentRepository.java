package com.mondia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mondia.entities.Contingent;
import com.mondia.entities.SubscribtionType;

@Transactional
public interface ContingentRepository extends CrudRepository<Contingent, Integer> {

	List<Contingent> findDistinctByTypeAndSubscribtionTypeSubscribtionsUserId(String type , Integer id);
}
