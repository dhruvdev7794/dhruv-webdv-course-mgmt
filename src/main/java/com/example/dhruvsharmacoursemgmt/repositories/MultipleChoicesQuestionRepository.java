package com.example.dhruvsharmacoursemgmt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.dhruvsharmacoursemgmt.model.MultipleChoiceQuestion;

public interface MultipleChoicesQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer> {
	
}
