package com.example.finalyearapp.RecycleMaterial;

public class Material {

    String Name;
    String What;
    String Where;
    String Why;
    String How;
    String tip;

    public String getTip() {
        return tip;
    }

    public Material() {
    }

    public Material(String what, String where, String why) {
        What = what;
        Where = where;
        Why = why;
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
        return What;
    }

    public void setWhat(String what) {
        this.What = what;
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
