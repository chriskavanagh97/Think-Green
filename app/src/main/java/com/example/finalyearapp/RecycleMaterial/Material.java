package com.example.finalyearapp.RecycleMaterial;

public class Material {

    String Name;
    String What;
    String Where;
    String Why;
    String How;
    String GeneralComment;

    public String getGeneralComment() {
        return GeneralComment;
    }

    public Material() {
    }

    public Material(String name, String what, String where, String why,String how, String generalcomment) {
        What = what;
        Where = where;
        Why = why;
        Name = name;
        How = how;
        GeneralComment = generalcomment;
    }
    public Material(String name)
    {

    }

    public void setGeneralComment(String GeneralComment) {
        this.GeneralComment = GeneralComment;
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
