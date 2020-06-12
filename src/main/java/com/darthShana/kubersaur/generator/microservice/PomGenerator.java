package com.darthShana.kubersaur.generator.microservice;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.FileWriter;
import java.io.IOException;

public class PomGenerator implements Generator {

    private String microserviceName;
    private String baseDirectory;
    private Org org;
    private String templateDirectory;

    String microserviceName(){
        return microserviceName;
    }

    @Override
    public String organisationPackage() {
        return organisationPackage();
    }

    public String organisationName(){
        return org.getName();
    }

    public PomGenerator(String microserviceName, String baseDirectory, String templateDirectory, Org org) {
        this.microserviceName = microserviceName;
        this.baseDirectory = baseDirectory;
        this.templateDirectory = templateDirectory;
        this.org = org;
    }

    @Override
    public void generate() throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDirectory +"pom.mustache");
        mustache.execute(new FileWriter(baseDirectory+"/"+"pom.xml"), this).flush();
    }
}
