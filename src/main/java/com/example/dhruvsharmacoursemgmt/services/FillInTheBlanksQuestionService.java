package com.example.dhruvsharmacoursemgmt.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.repositories.FillInTheBlanksRepository;
import com.example.dhruvsharmacoursemgmt.model.Exam;
import com.example.dhruvsharmacoursemgmt.model.FillInTheBlanksQuestion;
import com.example.dhruvsharmacoursemgmt.repositories.ExamRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class FillInTheBlanksQuestionService {
	@Autowired
	ExamRepository examRepo;
	
	@Autowired
	FillInTheBlanksRepository fillInBlanksRepo;
	
	@PostMapping("api/exam/{examId}/blanks")
	public FillInTheBlanksQuestion createFBQuestion(@PathVariable("examId") int examId,
									@RequestBody FillInTheBlanksQuestion question) {
		Optional<Exam> exam = examRepo.findById(examId);
		if(exam.isPresent()) {
			Exam newExam = exam.get();
			question.setExam(newExam);
			return fillInBlanksRepo.save(question);
		}
		return null;
	}

}
