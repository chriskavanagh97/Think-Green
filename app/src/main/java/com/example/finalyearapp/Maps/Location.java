package com.example.finalyearapp.Maps;

public class Location {

    String name;
    String address;
    String city;
    String state;
    String coordinantes;

    public Location(String name, String address, String city, String state, String coordinantes) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.coordinantes = coordinantes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCoordinantes() {
        return coordinantes;
    }

    public void setCoordinantes(String coordinantes) {
        this.coordinantes = coordinantes;
    }
}
