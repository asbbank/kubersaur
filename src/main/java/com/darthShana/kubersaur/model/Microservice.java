package com.darthShana.kubersaur.model;

public class Microservice {
    private String name;

    public Microservice(){}

    public Microservice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNoDash(){
        return name.replace("-", "_");
    }
}
