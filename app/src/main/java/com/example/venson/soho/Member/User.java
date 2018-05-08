package com.example.venson.soho.Member;

public class User {
    private int id;
    private String password;
    private String name;
    private String email;
    private int gender;

    public User(int id, String password, String name, String email) {
        super();
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String email, String password, String name,int gender) {
        super();

        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

}