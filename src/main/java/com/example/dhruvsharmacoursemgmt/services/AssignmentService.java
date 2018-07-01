package com.example.dhruvsharmacoursemgmt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Assignment;
import com.example.dhruvsharmacoursemgmt.model.Course;
import com.example.dhruvsharmacoursemgmt.model.Lesson;
import com.example.dhruvsharmacoursemgmt.model.Widget;
import com.example.dhruvsharmacoursemgmt.repositories.AssignmentRepository;
import com.example.dhruvsharmacoursemgmt.repositories.LessonRepository;

@RestController
@CrossOrigin(origins="*")
public class AssignmentService {
	@Autowired
	AssignmentRepository assgnRepo;
	
	@Autowired
	LessonRepository lessonRepo;
	
	@PutMapping("api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(@PathVariable("lessonId") int lessonId, @RequestBody Assignment assignment) {
		Optional<Lesson> data = lessonRepo.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			assignment.setLesson(lesson);
			return assgnRepo.save(assignment);
		}
		return null;
	}
	
	@PutMapping("api/assignment/{assignmentId}/")
	public Assignment updateAssignment(@PathVariable("assignmentId") int assignmentId, @RequestBody Assignment assignment) {
		Optional<Assignment> data = assgnRepo.findById(assignmentId);
		if(data.isPresent()) {
			return assgnRepo.save(assignment);
		}
		return null;
	}
	
	
	@GetMapping("api/assignment")
	public Iterable<Assignment> findAllAssignment(){
		return assgnRepo.findAll();
	}
	
	@GetMapping("api/lesson/{lessonId}/assignment")
	public Iterable<Assignment> findAssignmentsForLesson(@PathVariable("lessonId") int lessonId){
		Optional<Lesson> lesson = lessonRepo.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			List<Widget> widgets = newLesson.getWidgets();
			List<Assignment> assignList = new ArrayList();
			for(Widget widget: widgets) {
				if(widget.getWidgetType()=="Assignment") {
					assignList.add((Assignment) widget);
				}
			}
			
			return assignList;
		}
		return null;
	}
	
	@GetMapping("api/assignment/{assignId}")
	public Assignment getAssignmentFromId(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> assignment = assgnRepo.findById(assignmentId);
		if(assignment.isPresent()) {
			return assignment.get();
		}
		return null;
	}
	
	
}
