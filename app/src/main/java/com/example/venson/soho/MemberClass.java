package com.example.venson.soho;

/**
 * Created by venson on 2018/5/1.
 */

public class MemberClass {
    int id;
    String email;
    String password;
    String name;
    String description;
    String Skill;
    String gender;

    public MemberClass(int id, String email, String password, String name, String description, String skill, String gender) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.description = description;
        this.Skill = skill;
        this.gender = gender;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public String getSkill() {
        return Skill;
    }
    public void setSkill(String skill) {
        Skill = skill;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}
