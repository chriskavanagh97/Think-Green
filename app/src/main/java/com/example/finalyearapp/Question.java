package com.example.finalyearapp;


public class Question {
    public Question() {

    }

    private String Question;
    private String Difficulty;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String Category;
    private int Id;

    public void setCategory(String category) {
        Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    private String Description;
    private int score;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public Question(int id , String question, String difficulty, String answer1, String answer2, String answer3, String answer4, int score, String description, String category) {
        Question = question;
        Difficulty = difficulty;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        this.score = score;
        Description = description;
        Category = category;
        Id = id;

    }


}


