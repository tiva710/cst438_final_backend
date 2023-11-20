package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends 
                 CrudRepository<User1, Integer>{


	User1 findByAlias(String alias);
	User1 findById(String id);
}

