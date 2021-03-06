package com.example.dhruvsharmacoursemgmt.services;

import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("moduleId") int moduleId, @RequestBody Lesson lesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			lesson.setModule(module);
			return lessonRepository.save(lesson);
		}
		return null;
	}
	
//	@GetMapping("api/course/{courseId}/module/{moduleId}/lesson")
//	public Iterable<Lesson> findAllLessonsForModule(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId, HttpServletResponse response){
//		Optional<Module> data = moduleRepository.findById(moduleId);
//		if(data.isPresent()) {
//			Module module = data.get();
//			return module.getLessons();
//		}
//		response.setStatus(HttpServletResponse.SC_CONFLICT);
//		return null;
//	}
	
	@GetMapping("api/module/{moduleId}/lesson")
	public Iterable<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int moduleId, HttpServletResponse response){
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@PutMapping("api/lesson/{id}")
	public Lesson updateLesson(@PathVariable("lessonId") int lessonId, @RequestBody Lesson newLesson) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			lesson.setTitle(newLesson.getTitle());
			lessonRepository.save(lesson);
		}
		return null;
	}
}
