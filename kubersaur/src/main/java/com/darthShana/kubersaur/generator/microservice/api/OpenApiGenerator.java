package com.darthShana.kubersaur.generator.microservice.api;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OpenApiGenerator extends Generator {
    private final String baseDir;
    private final String templateDir;
    private final String swaggertBaseDir;

    public OpenApiGenerator(String microserviceName, String baseDir, String templateDir, Org org) {
        super(org, microserviceName);
        this.baseDir = baseDir;
        this.templateDir = templateDir;
        this.swaggertBaseDir =  this.baseDir + "/src/main/resources/";
    }

    public void generate() throws IOException {
        new File(swaggertBaseDir).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDir +"openapi.mustache");
        mustache.execute(new FileWriter(swaggertBaseDir+"/"+"openapi.yaml"), this).flush();
    }
}
