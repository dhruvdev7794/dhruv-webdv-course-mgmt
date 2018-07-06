package com.example.dhruvsharmacoursemgmt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Assignment;
import com.example.dhruvsharmacoursemgmt.model.Lesson;
import com.example.dhruvsharmacoursemgmt.model.Widget;
import com.example.dhruvsharmacoursemgmt.repositories.AssignmentRepository;
import com.example.dhruvsharmacoursemgmt.repositories.LessonRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class AssignmentService {
	@Autowired
	AssignmentRepository assgnRepo;
	
	@Autowired
	LessonRepository lessonRepo;
	
	@DeleteMapping("api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId) {
		assgnRepo.deleteById(assignmentId);	
	}
	
	@PostMapping("api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(@PathVariable("lessonId") int lessonId, @RequestBody Assignment assignment) {
		Optional<Lesson> data = lessonRepo.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			assignment.setLesson(lesson);
			return assgnRepo.save(assignment);
		}
		return null;
	}
	
	@PutMapping("api/lesson/{lessonId}/assignment")
	public Assignment updateAssignment(@PathVariable("lessonId") int lessonId, @RequestBody Assignment assignment) {
		Optional<Lesson> data = lessonRepo.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			assignment.setLesson(lesson);
			return assgnRepo.save(assignment);
		}
		return null;
	}
	
	
	@GetMapping("api/assignment")
	public Iterable<Assignment> findAllAssignment(){
		return assgnRepo.findAll();
	}
	
	@GetMapping("api/lesson/{lessonId}/assignment")
	public Iterable<Assignment> findAssignmentsForLesson(@PathVariable("lessonId") int lessonId, HttpServletResponse response){
		Optional<Lesson> lesson = lessonRepo.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			List<Widget> widgets = newLesson.getWidgets();
			List<Assignment> assignList = new ArrayList();
			for(Widget widget: widgets) {
				if(widget.getWidgetType().equals("Assignment")) {
					assignList.add((Assignment) widget);
				}
			}
			return assignList;
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@GetMapping("api/assignment/{assignmentId}")
	public Assignment getAssignmentFromId(@PathVariable("assignmentId") int assignmentId, HttpServletResponse response) {
		Optional<Assignment> assignment = assgnRepo.findById(assignmentId);
		if(assignment.isPresent()) {
			return assignment.get();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	
}
