package com.darthShana.kubersaur.generator.microservice.service.java;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DomainGenerator extends Generator {
    private final String baseDir;
    private final String templateDirectory;
    private final String filePath;

    public DomainGenerator(String microserviceName, String baseDir, String templateDirectory, Org org) {
        super(org, microserviceName);
        this.baseDir = baseDir;
        this.templateDirectory = templateDirectory;
        this.filePath = baseDir+"/src/main/java/"+org.getPackagePathDirs()+"/service";
    }

    public void generate() throws IOException {
        new File(filePath).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDirectory +"DomainService.mustache");
        mustache.execute(new FileWriter(filePath+"/"+"DomainService.java"), this).flush();
    }
}
