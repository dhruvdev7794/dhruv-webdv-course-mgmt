package com.example.dhruvsharmacoursemgmt.services;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	

	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			user.setUsername(newUser.getUsername());
			user.setDateOfBirth(newUser.getDateOfBirth());
			// do it for the rest of the fields
			userRepository.save(user);
			return user;
		}
		return null;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser) {
		Optional<User> data = userRepository.findById(newUser.getId());
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			user.setUsername(newUser.getUsername());
			user.setDateOfBirth(newUser.getDateOfBirth());
			// do it for the rest of the fields
			userRepository.save(user);
			return user;
		}
		return null;
	}
	
	
	@DeleteMapping("/api/user/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") int id) {
		try{
			userRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/api/login")
	public Optional<User> login(@RequestBody User user, HttpServletResponse response) {
		Optional<User> data = findUserByCredentials(user);
		if(data.isPresent()) {
			return data;
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	public Optional<User> findUserByCredentials(@RequestBody User user){
		return userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpServletResponse response){
		Iterable<User> data = findUserByUsername(user.getUsername());
		for(User newUser : data) {
			if(newUser!=null) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return null;
			}
			else {
				return createUser(user);
			}
		}
		return createUser(user);
	}
	
	public Iterable<User> findUserByUsername(@RequestBody String username){
		return userRepository.findUserByUsername(username);
	}
	
	@PostMapping("/api/logout")
	public void logout (HttpSession session) {
		session.invalidate();
	}

}
