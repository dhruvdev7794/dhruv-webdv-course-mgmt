package com.example.dhruvsharmacoursemgmt.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Course;
import com.example.dhruvsharmacoursemgmt.model.User;
import com.example.dhruvsharmacoursemgmt.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		Date date = new Date();
		if(course.getCreated() == null) {
			course.setCreated(date);
		}
		if(course.getModified() == null) {
			course.setModified(date);
		}
		return courseRepository.save(course);
	}
	
	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@PutMapping("/api/course/{userId}")
	public Course updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course newCourse) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
//			Course course = data.get();
//			course.setFirstName(newUser.getFirstName());
//			course.setLastName(newUser.getLastName());
//			course.setEmail(newUser.getEmail());
//			course.setPhone(newUser.getPhone());
//			course.setRole(newUser.getRole());
//			course.setUsername(newUser.getUsername());
//			course.setDateOfBirth(newUser.getDateOfBirth());
			// do it for the rest of the fields
			courseRepository.save(newCourse);
			return newCourse;
		}
		return null;
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}


}
