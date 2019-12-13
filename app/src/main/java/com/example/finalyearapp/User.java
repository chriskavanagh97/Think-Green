package com.example.finalyearapp;


public class User {


    String firstName;
    String username;
    String email;
    private int score;




    public User(String firstName, String username, String email , Integer score) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.score = score;
    }
    public User(String username, String email, int score ){
        this.username = username;
        this.email = email;
        this.score = score;

    }
    public User(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        score = score;
    }


}

