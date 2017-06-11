package com.mondia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mondia.entities.User;


@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByEmail(String email);
}
