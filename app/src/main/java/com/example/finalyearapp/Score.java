package com.example.finalyearapp;


import java.util.Map;

class Score {


    private String Username;
    private int Score;
    private Map<String, String> timestamp;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Map<String, String> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Map<String, String> timestamp) {
        this.timestamp = timestamp;
    }

    public Score(String username, int score, Map<String, String> timestamp) {
        Username = username;
        Score = score;
        this.timestamp = timestamp;
    }
}