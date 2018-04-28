package com.example.venson.soho.Home;

import java.io.Serializable;
import java.sql.Date;

public class Case implements Serializable {
	int id;
	double budget;
	String name;
	String description;
	Date recruit_start;
	Date recruit_end;
	
	public Case(int id, double budget, String name, String description, Date recruit_start, Date recruit_end) {
		super();
		this.id = id;
		this.budget = budget;
		this.name = name;
		this.description = description;
		this.recruit_start = recruit_start;
		this.recruit_end = recruit_end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRecruit_start() {
		return recruit_start;
	}

	public void setRecruit_start(Date recruit_start) {
		this.recruit_start = recruit_start;
	}

	public Date getRecruit_end() {
		return recruit_end;
	}

	public void setRecruit_end(Date recruit_end) {
		this.recruit_end = recruit_end;
	}
	
	
	
}
