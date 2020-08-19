package com.darthShana.kubersaur.model;

import com.darthShana.kubersaur.cli.Language;

public class Microservice {
    private String name;
    private Language language;

    public Microservice(){}

    public Microservice(String name, Language language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    public String getNameNoDash(){
        return name.replace("-", "_");
    }

    public boolean isCSHARPProject(){
        return language ==Language.CSHARP;
    }
}
