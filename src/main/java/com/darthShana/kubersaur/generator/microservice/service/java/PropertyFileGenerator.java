package com.darthShana.kubersaur.generator.microservice.service.java;

import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PropertyFileGenerator {
    private final String name;
    private final String baseDir;
    private final String templateDirectory;
    private final Org org;
    private final String filePath;

    public PropertyFileGenerator(String name, String baseDir, String templateDirectory, Org org) {
        this.name = name;
        this.baseDir = baseDir;
        this.templateDirectory = templateDirectory;
        this.org = org;
        this.filePath = baseDir+"/src/test/resources/";
    }

    public void generate() throws IOException {
        new File(filePath).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDirectory +"MicroserviceProperties.mustache");
        mustache.execute(new FileWriter(filePath+"/"+name+"-service.properties"), this).flush();
    }
}
