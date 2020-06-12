package com.darthShana.kubersaur.generator.microservice;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Microservice;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import mustachejava.Pom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReactorPomGenerator implements Generator {


    private final String baseDir;
    private Org org;

    public ReactorPomGenerator(Org org, String baseDir) {
        this.org = org;
        this.baseDir = baseDir;
    }

    public List<Microservice> microservices(){
        return org.getMicroservices();
    }

    @Override
    public String organisationPackage() {
        return org.getPackagePath();
    }

    @Override
    public String organisationName() {
        return org.getName();
    }

    @Override
    public void generate() throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(this.baseDir+"/reactor-pom.mustache");
        mustache.execute(new FileWriter(this.baseDir+"/pom.xml"), this).flush();
    }
}
