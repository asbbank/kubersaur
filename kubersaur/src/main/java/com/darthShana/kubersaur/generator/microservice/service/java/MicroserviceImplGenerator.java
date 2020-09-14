package com.darthShana.kubersaur.generator.microservice.service.java;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.generator.core.FileGeneratorBuilder;
import com.darthShana.kubersaur.model.Org;
import org.kubersaur.codegen.Language;

import java.io.IOException;

public class MicroserviceImplGenerator extends Generator implements org.kubersaur.codegen.implementation.CodegenConfig{
    protected String baseDir;
    protected String templateDirectory;

    public MicroserviceImplGenerator(){}

    @Override
    public void init(String name, String implementationBaseDirectory, String templateDir, Org org) {
        this.microserviceName = name;
        this.baseDir = implementationBaseDirectory;
        this.templateDirectory = templateDir+"java/";

        this.org = org;
    }

    public void generate() throws IOException {

        new FileGeneratorBuilder("pom.xml")
                .atLocation(baseDir)
                .withTemplate(templateDirectory+"pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("Application.java")
                .atLocation(baseDir+"src/main/java/"+org.getPackagePathDirs()+"/"+org.getName()+"/"+microserviceName+"/")
                .withTemplate(templateDirectory+"Application.mustache")
                .generate(this);

        new FileGeneratorBuilder(microserviceNameCamelCase()+"DinosaurResource.java")
                .atLocation(baseDir+"src/main/java/"+org.getPackagePathDirs()+"/"+org.getName()+"/"+microserviceName+"/"+"api/")
                .withTemplate(templateDirectory+"DinosaurResource.mustache")
                .generate(this);

        new FileGeneratorBuilder("DomainService.java")
                .atLocation(baseDir+"src/main/java/"+org.getPackagePathDirs()+"/"+org.getName()+"/"+microserviceName+"/"+"service/")
                .withTemplate(templateDirectory+"DomainService.mustache")
                .generate(this);

        new FileGeneratorBuilder("BatheRunner.groovy")
                .atLocation(baseDir+"src/test/groovy/"+org.getPackagePathDirs()+"/"+org.getName()+"/"+microserviceName+"/")
                .withTemplate(templateDirectory+"BatheRunner.mustache")
                .generate(this);

        new FileGeneratorBuilder(microserviceName+"-service.properties")
                .atLocation(baseDir+"src/test/resources/")
                .withTemplate(templateDirectory+"MicroserviceProperties.mustache")
                .generate(this);

    }

    @Override
    public String getName() {
        return "java";
    }

    @Override
    public Language getLanguage() {
        return Language.JAVA;
    }


}
