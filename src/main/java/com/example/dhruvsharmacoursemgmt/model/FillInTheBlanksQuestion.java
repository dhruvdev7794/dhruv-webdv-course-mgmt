package com.example.dhruvsharmacoursemgmt.model;

import javax.persistence.Entity;

@Entity
public class FillInTheBlanksQuestion extends Question{
	private String questionText;
	private String questionTxtArr;
	private String variable;
	private String answer;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionTxtArr() {
		return questionTxtArr;
	}

	public void setQuestionTxtArr(String questionTxtArr) {
		this.questionTxtArr = questionTxtArr;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
