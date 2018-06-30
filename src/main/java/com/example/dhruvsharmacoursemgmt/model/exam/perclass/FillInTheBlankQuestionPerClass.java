package com.example.dhruvsharmacoursemgmt.model.exam.perclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.dhruvsharmacoursemgmt.model.exam.perclass.BaseQuestionPerClass;

@Entity
@Table(name = "PER_CLASS_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlankQuestionPerClass extends BaseQuestionPerClass {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	
}
