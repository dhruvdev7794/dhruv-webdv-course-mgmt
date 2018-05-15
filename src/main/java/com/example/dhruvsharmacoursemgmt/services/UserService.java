package com.example.dhruvsharmacoursemgmt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.User;
import com.example.dhruvsharmacoursemgmt.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;	
	
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> data = userRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
}
