package com.darthShana.kubersaur.generator.microservice.service.java;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ApplicationLauncherGenerator extends Generator {
    private final String templateDirectory;
    private final String filePath;

    public ApplicationLauncherGenerator(String microserviceName, String baseDir, String templateDirectory, Org org) {
        super(org, microserviceName);
        this.templateDirectory = templateDirectory;
        this.filePath = baseDir+"/src/test/groovy/"+org.getPackagePathDirs();
    }

    @Override
    public void generate() throws IOException {
        new File(filePath).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDirectory +"BatheRunner.mustache");
        mustache.execute(new FileWriter(filePath+"/"+"BatheRunner.groovy"), this).flush();
    }
}
