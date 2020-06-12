package com.darthShana.kubersaur.generator.microservice.api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SwaggerGenerator {
    private final String microserviceName;
    private final String baseDir;
    private final String templateDir;
    private final String swaggertBaseDir;

    public SwaggerGenerator(String microserviceName, String baseDir, String templateDir) {
        this.microserviceName = microserviceName;
        this.baseDir = baseDir;
        this.templateDir = templateDir;
        this.swaggertBaseDir =  this.baseDir + "/src/main/resources/";
    }

    String microserviceName(){
        return microserviceName;
    }

    public void generate() throws IOException {
        new File(swaggertBaseDir).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templateDir +"swagger.mustache");
        mustache.execute(new FileWriter(swaggertBaseDir+"/"+"swagger.yaml"), this).flush();
    }
}
