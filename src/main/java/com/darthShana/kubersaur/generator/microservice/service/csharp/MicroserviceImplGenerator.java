package com.darthShana.kubersaur.generator.microservice.service.csharp;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.generator.core.FileGeneratorBuilder;
import com.darthShana.kubersaur.model.Org;

import java.io.File;
import java.io.IOException;

public class MicroserviceImplGenerator extends Generator {
    private final String name;
    private final String baseDirectory;
    private final String templateDirectory;
    private final Org org;

    public MicroserviceImplGenerator(String name, String baseDirectory, String templateDirectory, Org org) {
        super(org, name);
        this.name = name;
        this.baseDirectory = baseDirectory;
        this.templateDirectory = templateDirectory;
        this.org = org;
    }

    public void generate() throws IOException {
        new File(baseDirectory+"bin/").mkdirs();
        new File(baseDirectory+"Models/").mkdirs();
        new File(baseDirectory+"obj/").mkdirs();
        new File(baseDirectory+"Properties/").mkdirs();
        new File(baseDirectory+"target/").mkdirs();


        new FileGeneratorBuilder(name+"-service.csproj")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"serviceCSProj.mustache")
                .generate(this);

        new FileGeneratorBuilder("Program.cs")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"Program.mustache")
                .generate(this);

        new FileGeneratorBuilder("Startup.cs")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"Startup.mustache")
                .generate(this);

        new FileGeneratorBuilder("nuget.config")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"Startup.mustache")
                .generate(this);

        new FileGeneratorBuilder("appsettings.json")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"appsettings.mustache")
                .generate(this);

        new FileGeneratorBuilder(".dockerignore")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"dockerignore.mustache")
                .generate(this);

        new FileGeneratorBuilder("DinosaurController.cs")
                .atLocation(baseDirectory+"Controller/")
                .withTemplate(templateDirectory+"DinosaurController.mustache")
                .generate(this);

    }
}
