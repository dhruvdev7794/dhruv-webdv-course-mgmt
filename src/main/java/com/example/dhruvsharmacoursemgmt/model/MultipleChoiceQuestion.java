package com.example.dhruvsharmacoursemgmt.model;

import javax.persistence.Entity;

@Entity
public class MultipleChoiceQuestion extends Question {
	private String options;
	private String correctOption;
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
}
