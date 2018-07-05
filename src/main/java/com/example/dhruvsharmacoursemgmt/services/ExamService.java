package com.example.dhruvsharmacoursemgmt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Assignment;
import com.example.dhruvsharmacoursemgmt.model.Exam;
import com.example.dhruvsharmacoursemgmt.model.Lesson;
import com.example.dhruvsharmacoursemgmt.model.Question;
import com.example.dhruvsharmacoursemgmt.model.Widget;
import com.example.dhruvsharmacoursemgmt.repositories.ExamRepository;
import com.example.dhruvsharmacoursemgmt.repositories.LessonRepository;


@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class ExamService {
	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	LessonRepository lessonRepo;
	
	@GetMapping("api/exam")
	public Iterable<Exam> findAllExams(){
		return examRepo.findAll();
	}
	
	@GetMapping("api/lesson/{lessonId}/exam")
	public Iterable<Exam> findExamForLesson(@PathVariable("lessonId") int lessonId){
		Optional<Lesson> lesson = lessonRepo.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			List<Widget> widgets = newLesson.getWidgets();
			List<Exam> examList = new ArrayList();
			for(Widget widget: widgets) {
				if(widget.getWidgetType().equals("Exam")) {
					examList.add((Exam) widget);
				}
			}
			return examList;
		}
		return null;
	}
	
	@GetMapping("api/exam/{examId}")
	public Exam getAssignmentFromId(@PathVariable("examId") int examId, HttpServletResponse response) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			return exam.get();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@PostMapping("api/lesson/{lessonId}/exam")
	public Exam createExam(@PathVariable("lessonId") int lessonId, @RequestBody Exam exam) {
		Optional<Lesson> data = lessonRepo.findById(lessonId);
		if(data.isPresent()) {
			Lesson newLesson = data.get();
			exam.setLesson(newLesson);
			return examRepo.save(exam);
		}
		return null;
	}
	
	@GetMapping("api/exam/{examId}/question")
	public List<Question> findQuestionsForExam(@PathVariable("examId") int examId){
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			Exam newExam = exam.get();
			return newExam.getQuestions();
		}
		return null;
	}
}