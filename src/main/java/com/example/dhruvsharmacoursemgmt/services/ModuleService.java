package com.example.dhruvsharmacoursemgmt.services;

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
import com.example.dhruvsharmacoursemgmt.model.Module;
import com.example.dhruvsharmacoursemgmt.repositories.CourseRepository;
import com.example.dhruvsharmacoursemgmt.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
			@PathVariable("courseId") int courseId,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);
		}
		return null;
	}
	
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}

	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteCourse(@PathVariable("moduleId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	}
	
	@GetMapping("/api/module")
	public Iterable<Module> findAllModules(){
		return moduleRepository.findAll();
	}
	
	@GetMapping("/api/module/{id}")
	public Module findModuleById(@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/module/{id}")
	public Module updateModule(@PathVariable("moduleId") int moduleId, @RequestBody Module newModule) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			module.setTitle(newModule.getTitle());
			return moduleRepository.save(module);
		}
		return null;
	}
	

}
