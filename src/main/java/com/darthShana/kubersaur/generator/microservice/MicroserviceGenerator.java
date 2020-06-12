package com.darthShana.kubersaur.generator.microservice;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.generator.microservice.api.MicroserviceApiGenerator;
import com.darthShana.kubersaur.generator.microservice.helm.HelmChartGenerator;
import com.darthShana.kubersaur.generator.microservice.service.MicroserviceImplGenerator;
import com.darthShana.kubersaur.model.Microservice;
import com.darthShana.kubersaur.model.Org;

import java.io.File;
import java.io.IOException;

public class MicroserviceGenerator {

    private final String name;
    private final String implementationBaseDirectory;
    private final String interfaceBaseDirectory;
    private final String interfaceParentDir;
    private final String implementationParentDir;
    private Org org;

    public MicroserviceGenerator(String name, Org org) {
        this.name = name;
        this.org = org;
        this.interfaceParentDir = "code/api/";
        this.implementationParentDir = "code/service/";
        this.implementationBaseDirectory = implementationParentDir+this.name+"-service";
        this.interfaceBaseDirectory = interfaceParentDir+this.name+"-api";
    }

    public void generate() throws IOException {
        org.addMicroservice(new Microservice(name));

        new File(implementationBaseDirectory).mkdirs();
        new File(interfaceBaseDirectory).mkdirs();
        new MicroserviceApiGenerator(name, interfaceBaseDirectory, interfaceParentDir, org).generate();
        new MicroserviceImplGenerator(name, implementationBaseDirectory, implementationParentDir, org).generate();

        new ReactorPomGenerator(org, interfaceParentDir).generate();
        new ReactorPomGenerator(org, implementationParentDir).generate();

        new HelmChartGenerator(name, org).generate();

    }
}
