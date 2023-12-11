package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends 
                 CrudRepository<User, Integer>{

	User findByusername(String username);
}