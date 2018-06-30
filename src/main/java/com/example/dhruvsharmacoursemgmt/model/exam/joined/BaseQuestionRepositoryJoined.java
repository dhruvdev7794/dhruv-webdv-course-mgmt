package com.example.dhruvsharmacoursemgmt.model.exam.joined;

import org.springframework.data.repository.CrudRepository;

import com.example.dhruvsharmacoursemgmt.model.exam.single.BaseQuestionSingle;

public interface BaseQuestionRepositoryJoined extends CrudRepository<BaseQuestionJoined, Integer>{

}
