package com.example.dhruvsharmacoursemgmt.model;

import javax.persistence.Entity;

@Entity
public class EssayQuestion extends Question{
	private String essay;

	public String getEssay() {
		return essay;
	}

	public void setEssay(String essay) {
		this.essay = essay;
	}
	
}
