package com.ekz.shopeeng.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekz.shopeeng.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query(value = "SELECT n FROM User n WHERE n.email = ?1 AND n.password = ?2")
	User userLogin(String email, String password);
	
}
