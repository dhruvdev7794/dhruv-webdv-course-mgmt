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
		Optional<User> data = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		if(data.isPresent()) {
			return data;
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpServletResponse response){
		Iterable<User> data = userRepository.findUserByUsername(user.getUsername());
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
	
	@GetMapping("/api/session/set/{attr}/{value}")
	public String setSessionAttribute(
			@PathVariable("attr") String attr,
			@PathVariable("value") String value,
			HttpSession session) {
		session.setAttribute(attr, value);
		return attr + " = " + value;
	}
	
	@GetMapping("/api/session/get/{attr}")
	public String getSessionAttribute(
			@PathVariable ("attr") String attr,
			HttpSession session) {
		return (String) session.getAttribute(attr);
	}

	@GetMapping("/api/session/invalidate")
	public String invalidateSession(
	HttpSession session) {
		session.invalidate();
	return "session invalidated";
	}


	
	
}
