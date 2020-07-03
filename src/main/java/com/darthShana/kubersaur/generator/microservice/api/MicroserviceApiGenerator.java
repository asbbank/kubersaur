package com.darthShana.kubersaur.generator.microservice.api;

import com.darthShana.kubersaur.generator.microservice.PomGenerator;
import com.darthShana.kubersaur.model.Org;

import java.io.IOException;

public class MicroserviceApiGenerator {
    private final String name;
    private final String baseDir;
    private final Org org;
    private final String templateDir;

    public MicroserviceApiGenerator(String name, String baseDir, String templateDir, Org org) {
        this.name = name;
        this.baseDir = baseDir;
        this.templateDir = templateDir;
        this.org = org;
    }

    public void generate() throws IOException {
        new PomGenerator(name, baseDir, templateDir, org).generate();
        new OpenApiGenerator(name, baseDir, templateDir, org).generate();
    }
}
