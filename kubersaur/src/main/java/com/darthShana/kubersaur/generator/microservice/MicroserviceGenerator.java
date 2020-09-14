package com.darthShana.kubersaur.generator.microservice;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.generator.microservice.api.MicroserviceApiGenerator;
import com.darthShana.kubersaur.generator.microservice.helm.HelmChartGenerator;
import com.darthShana.kubersaur.model.Microservice;
import com.darthShana.kubersaur.model.Org;
import org.kubersaur.codegen.Language;
import org.kubersaur.codegen.implementation.CodegenConfig;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MicroserviceGenerator {

    private final String name;
    private final String implementationBaseDirectory;
    private final String interfaceBaseDirectory;
    private final String interfaceParentDir;
    private final String implementationParentDir;
    private final Language language;
    private Org org;
    private Set<String> generatorOverrides;

    private static ServiceLoader<CodegenConfig> implementationLoader = ServiceLoader.load(CodegenConfig.class);
    private Map<Language, CodegenConfig> implementationGenerator = new HashMap<>();

    public MicroserviceGenerator(String name, Language language, Org org, Set<String> generatorOverrides) {
        this.name = name;
        this.language = language;
        this.org = org;
        this.generatorOverrides = generatorOverrides;
        this.interfaceParentDir = "code/api/";
        this.implementationParentDir = "code/service/";
        this.implementationBaseDirectory = implementationParentDir+this.name+"-service/";
        this.interfaceBaseDirectory = interfaceParentDir+this.name+"-api/";

        init();
        System.out.println(generatorOverrides);
    }

    void init(){
        implementationLoader.forEach(l -> {
            if(!implementationGenerator.containsKey(l.getLanguage()) || generatorOverrides.contains(l.getName())) {
                implementationGenerator.put(l.getLanguage(), l);
            }
        });
    }

    public void generate() throws IOException {
        org.addMicroservice(new Microservice(name, language));

        new File(implementationBaseDirectory).mkdirs();
        new File(interfaceBaseDirectory).mkdirs();
        new MicroserviceApiGenerator(name, interfaceBaseDirectory, interfaceParentDir, org).generate();

        CodegenConfig codegenConfig = implementationGenerator.get(language);
        if(codegenConfig == null){
            throw new IllegalArgumentException("implementation language:"+language+" not supported");
        }
        codegenConfig.init(name, implementationBaseDirectory, implementationParentDir, org);
        ((Generator) codegenConfig).generate();

        new ReactorPomGenerator(org, interfaceParentDir).generate();
        new ReactorPomGenerator(org, implementationParentDir).generate();

        new HelmChartGenerator(name, org).generate();

    }
}
