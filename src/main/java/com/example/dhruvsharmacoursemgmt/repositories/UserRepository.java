package com.example.dhruvsharmacoursemgmt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.dhruvsharmacoursemgmt.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	Optional<User> findUserByCredentials(
	@Param("username") String username, 
	@Param("password") String password);
	

}
