package com.example.dhruvsharmacoursemgmt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.dhruvsharmacoursemgmt.model.Exam;
import com.example.dhruvsharmacoursemgmt.model.Widget;

public interface ExamRepository
extends CrudRepository<Exam, Integer>{

}