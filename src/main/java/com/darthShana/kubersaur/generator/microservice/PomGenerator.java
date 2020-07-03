package com.darthShana.kubersaur.generator.microservice;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.FileWriter;
import java.io.IOException;

public class PomGenerator extends Generator {

    private String baseDirectory;
    private String templateDirectory;

    public PomGenerator(String microserviceName, String baseDirectory, String templateDirectory, Org org) {
        super(org, microserviceName);
        this.baseDirectory = baseDirectory;
        this.templateDirectory = templateDirectory;
    }


    public String organisationName(){
        return org.getName();
    }


    @Override
    public void generate() throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDirectory +"pom.mustache");
        mustache.execute(new FileWriter(baseDirectory+"/"+"pom.xml"), this).flush();
    }
}
