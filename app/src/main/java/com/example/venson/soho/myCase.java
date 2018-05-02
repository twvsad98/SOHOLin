package com.example.venson.soho;

import java.io.Serializable;
import java.util.Date;

public class myCase implements Serializable{
	int id;
	int budget;
	String name;
	String skill;
	String location;
	String description;
	Date recruit_start;
	Date recruit_end;
	String category;
	String user_name;

	public myCase(int id, int budget, String name, String skill, String location, String description, Date recruit_start, Date recruit_end, String category, String user_name) {
		this.id = id;
		this.budget = budget;
		this.name = name;
		this.skill = skill;
		this.location = location;
		this.description = description;
		this.recruit_start = recruit_start;
		this.recruit_end = recruit_end;
		this.category = category;
		this.user_name = user_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
