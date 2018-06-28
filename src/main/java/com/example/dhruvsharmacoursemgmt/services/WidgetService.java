package com.example.dhruvsharmacoursemgmt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Lesson;
import com.example.dhruvsharmacoursemgmt.model.Widget;
import com.example.dhruvsharmacoursemgmt.repositories.LessonRepository;
import com.example.dhruvsharmacoursemgmt.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public Iterable<Widget> findAllWidgets(@PathVariable("lessonId") int lessonId){
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			return newLesson.getWidgets();
		}
		
		return null;
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveAllWidgets(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> widgets) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			List<Widget> widgetList = newLesson.getWidgets();
			widgetRepository.deleteAll(widgetList);
			for(Widget widget: widgets) {
				widget.setLesson(newLesson);
				widgetRepository.save(widget);
			}
		}
	}
}
