package com.darthShana.kubersaur.generator.microservice.service.csharp;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.generator.core.FileGeneratorBuilder;
import com.darthShana.kubersaur.model.Org;
import org.kubersaur.codegen.Language;

import java.io.File;
import java.io.IOException;

public class MicroserviceImplGenerator extends Generator implements org.kubersaur.codegen.implementation.CodegenConfig{
    private String baseDirectory;
    private String templateDirectory;

    public MicroserviceImplGenerator(){}

    @Override
    public void init(String name, String implementationBaseDirectory, String templateDir, Org org) {
        this.microserviceName = name;
        this.baseDirectory = implementationBaseDirectory;
        this.templateDirectory = templateDir+"csharp/";
        this.org = org;
    }

    @Override
    public String getName() {
        return "csharp";
    }

    @Override
    public Language getLanguage() {
        return Language.CSHARP;
    }

    public void generate() throws IOException {
        new File(baseDirectory+"bin/").mkdirs();
        new File(baseDirectory+"Models/").mkdirs();
        new File(baseDirectory+"obj/").mkdirs();
        new File(baseDirectory+"target/").mkdirs();


        new FileGeneratorBuilder(microserviceName+"-service.csproj")
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
                .withTemplate(templateDirectory+"nuget.mustache")
                .generate(this);

        new FileGeneratorBuilder("appsettings.json")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"appsettings.mustache")
                .generate(this);

        new FileGeneratorBuilder("launchSettings.json")
                .atLocation(baseDirectory+"Properties/")
                .withTemplate(templateDirectory+"launchSettings.mustache")
                .generate(this);

        new FileGeneratorBuilder(".dockerignore")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"dockerignore.mustache")
                .generate(this);

        new FileGeneratorBuilder("Dockerfile")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"Dockerfile.mustache")
                .generate(this);

        new FileGeneratorBuilder("DinosaurController.cs")
                .atLocation(baseDirectory+"Controller/")
                .withTemplate(templateDirectory+"DinosaurController.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation(baseDirectory)
                .withTemplate(templateDirectory+"pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("launch.json")
                .atLocation(".vscode/")
                .withTemplate(templateDirectory+"launch.mustache")
                .generate(this);

        new FileGeneratorBuilder("tasks.json")
                .atLocation(".vscode/")
                .withTemplate(templateDirectory+"tasks.mustache")
                .generate(this);


    }


}
