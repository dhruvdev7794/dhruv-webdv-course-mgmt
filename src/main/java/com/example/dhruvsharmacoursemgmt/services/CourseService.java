package com.example.dhruvsharmacoursemgmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.repositories.CourseRepository;

@RestController
public class CourseService {

	@Autowired
	CourseRepository courseRepo;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses(){
		return courseRepo.findAll();
	}
	
}
