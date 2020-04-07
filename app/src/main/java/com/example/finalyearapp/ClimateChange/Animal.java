package com.example.finalyearapp.ClimateChange;

public class Animal {

    String name;

    public Animal(String name, String pictre) {
        this.name = name;
        this.pictre = pictre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictre() {
        return pictre;
    }

    public void setPictre(String pictre) {
        this.pictre = pictre;
    }

    String pictre;
}
