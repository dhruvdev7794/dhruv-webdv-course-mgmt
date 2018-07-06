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

import com.example.dhruvsharmacoursemgmt.model.Assignment;
import com.example.dhruvsharmacoursemgmt.model.EssayQuestion;
import com.example.dhruvsharmacoursemgmt.model.Exam;
import com.example.dhruvsharmacoursemgmt.repositories.EssayRepository;
import com.example.dhruvsharmacoursemgmt.repositories.ExamRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class EssayService {
	@Autowired
	EssayRepository essayRepo;
	
	@Autowired
	ExamRepository examRepo;
	
	@PostMapping("api/exam/{examId}/essay")
	public EssayQuestion createEssayQuestion(@PathVariable("examId") int examId,
									@RequestBody EssayQuestion question) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			Exam newExam = exam.get();
			question.setExam(newExam);
			return essayRepo.save(question);
		}
		return null;
	}
	
	@PutMapping("api/exam/{examId}/essay")
	public EssayQuestion updateEssayQuestion(@PathVariable("examId") int examId,
									@RequestBody EssayQuestion question) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			Exam newExam = exam.get();
			question.setExam(newExam);
			return essayRepo.save(question);
		}
		return null;
	}
	
	@GetMapping("api/essay/{questionId}")
	public EssayQuestion findEssayFromQuestionId(@PathVariable("questionId") int questionId, HttpServletResponse response) {
		Optional<EssayQuestion> essay = essayRepo.findById(questionId);
		if(essay.isPresent()) {
			return essay.get();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}

}
