package com.darthShana.kubersaur.cli;

import com.beust.jcommander.Parameter;
import com.darthShana.kubersaur.generator.microservice.MicroserviceGenerator;
import com.darthShana.kubersaur.model.Org;
import org.kubersaur.codegen.Language;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class CreateMicroservice implements RunnableCommand{

    @Parameter(description = "action")
    String workload;

    @Parameter(names = "--name", required = true)
    String name;

    @Parameter(names = "--language", required = true, converter = LanguageConverter.class)
    Language language;

    public void run(Org org, Yaml yaml) throws IOException {


        MicroserviceGenerator microserviceGenerator = new MicroserviceGenerator(name, language, org);
        microserviceGenerator.generate();

        FileWriter writer = new FileWriter("kubersaur.yaml");
        yaml.dump(org, writer);
        writer.flush();
    }

}
