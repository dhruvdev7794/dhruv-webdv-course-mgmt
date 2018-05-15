package com.example.dhruvsharmacoursemgmt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String role;
	private Date dateOfBirth;
	
	// setter and getter for id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	// setter and getter for username
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	// setter and getter for password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// setter and getter for first name
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	// Setter and getter for last name
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	// Setter and getter for email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	// Setter and getter for phone
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	// Setter and getter for role
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
//	// Setter and getter for DOB
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
