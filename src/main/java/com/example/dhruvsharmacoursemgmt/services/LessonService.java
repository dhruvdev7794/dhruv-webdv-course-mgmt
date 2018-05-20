package com.example.dhruvsharmacoursemgmt.services;

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
import com.example.dhruvsharmacoursemgmt.model.Lesson;
import com.example.dhruvsharmacoursemgmt.model.Module;
import com.example.dhruvsharmacoursemgmt.repositories.LessonRepository;
import com.example.dhruvsharmacoursemgmt.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons(){
		return lessonRepository.findAll();
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@DeleteMapping("api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int lessonId) {
		lessonRepository.deleteById(lessonId);
	}
	
	@PostMapping("api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@RequestBody Lesson lesson) {
		return lessonRepository.save(lesson);
	}
	
	
}
