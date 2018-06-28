package com.example.dhruvsharmacoursemgmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.dhruvsharmacoursemgmt.repositories.ExamRepository;
import com.example.dhruvsharmacoursemgmt.repositories.MultipleChoiceQuestionRepository;
import com.example.dhruvsharmacoursemgmt.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	TrueFalseQuestionRepository tfQuestionRepo;
	
	@Autowired
	MultipleChoiceQuestionRepository mcqRepo;
	
	
}
