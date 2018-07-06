package com.example.dhruvsharmacoursemgmt.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.model.Exam;
import com.example.dhruvsharmacoursemgmt.model.MultipleChoiceQuestion;
import com.example.dhruvsharmacoursemgmt.repositories.ExamRepository;
import com.example.dhruvsharmacoursemgmt.repositories.MultipleChoicesQuestionRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class MultipleChoiceQuestionService {
	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	MultipleChoicesQuestionRepository mcqRepo;
	
	@PostMapping("api/exam/{examId}/choice")
	public MultipleChoiceQuestion createMCQQuestion(@PathVariable("examId") int examId,
									@RequestBody MultipleChoiceQuestion question) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			System.out.println(question.getCorrectOption());
			System.out.println(question.getOptions());
			Exam newExam = exam.get();
			question.setExam(newExam);
			return mcqRepo.save(question);
		}
		return null;
	}

}
