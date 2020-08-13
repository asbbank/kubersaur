package com.darthShana.kubersaur.generator.microservice.service.java;

import com.darthShana.kubersaur.generator.microservice.PomGenerator;
import com.darthShana.kubersaur.model.Org;

import java.io.IOException;

public class MicroserviceImplGenerator {
    private final String name;
    private final String baseDir;
    private final String templateDirectory;
    private final Org org;

    public MicroserviceImplGenerator(String name, String baseDir, String templateDirectory, Org org) {
        this.name = name;
        this.baseDir = baseDir;
        this.templateDirectory = templateDirectory;
        this.org = org;
    }

    public void generate() throws IOException {
        new PomGenerator(name, baseDir, templateDirectory, org).generate();
        new AppRunnerGenerator(name, baseDir, templateDirectory, org).generate();
        new RestResourceGenerator(name, baseDir, templateDirectory, org).generate();
        new DomainGenerator(name, baseDir, templateDirectory, org).generate();
        new ApplicationLauncherGenerator(name, baseDir, templateDirectory, org).generate();
        new PropertyFileGenerator(name, baseDir, templateDirectory, org).generate();
    }
}
