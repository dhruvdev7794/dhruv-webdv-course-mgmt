package com.example.dhruvsharmacoursemgmt.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.EssayQuestion;
import com.example.dhruvsharmacoursemgmt.model.Exam;
import com.example.dhruvsharmacoursemgmt.model.TrueFalseQuestion;
import com.example.dhruvsharmacoursemgmt.repositories.ExamRepository;
import com.example.dhruvsharmacoursemgmt.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class TrueFalseService {
	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	TrueFalseQuestionRepository tfRepo;
	
	@PostMapping("api/exam/{examId}/truefalse")
	public TrueFalseQuestion createEssayQuestion(@PathVariable("examId") int examId,
									@RequestBody TrueFalseQuestion question) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			Exam newExam = exam.get();
			question.setExam(newExam);
			return tfRepo.save(question);
		}
		return null;
	}
	@PutMapping("api/exam/{examId}/truefalse")
	public TrueFalseQuestion updateEssayQuestion(@PathVariable("examId") int examId,
									@RequestBody TrueFalseQuestion question) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			Exam newExam = exam.get();
			question.setExam(newExam);
			return tfRepo.save(question);
		}
		return null;
	}
	
	@GetMapping("api/truefalse/{questionId}")
	public TrueFalseQuestion findEssayFromQuestionId(@PathVariable("questionId") int questionId, HttpServletResponse response) {
		Optional<TrueFalseQuestion> tfObj = tfRepo.findById(questionId);
		if(tfObj.isPresent()) {
			return tfObj.get();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}


}
