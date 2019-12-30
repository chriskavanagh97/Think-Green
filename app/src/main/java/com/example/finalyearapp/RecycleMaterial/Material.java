package com.example.finalyearapp.RecycleMaterial;

public class Material {

    String Name;
    String what;
    String Where;
    String Why;
    String How;
    String tip;

    public String getTip() {
        return tip;
    }

    public Material() {
    }

    public Material(String name) {
        Name = name;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhere() {
        return Where;
    }

    public void setWhere(String where) {
        Where = where;
    }

    public String getWhy() {
        return Why;
    }

    public void setWhy(String why) {
        Why = why;
    }

    public String getHow() {
        return How;
    }

    public void setHow(String how) {
        How = how;
    }
}
