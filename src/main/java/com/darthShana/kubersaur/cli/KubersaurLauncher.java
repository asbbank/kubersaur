package com.darthShana.kubersaur.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.darthShana.kubersaur.generator.microservice.MicroserviceGenerator;
import com.darthShana.kubersaur.model.Org;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KubersaurLauncher {

    public static void main(String ... argv) throws IOException {
        CommandMicroservice microservice = new CommandMicroservice();

        KubersaurLauncher main = new KubersaurLauncher();
        JCommander.newBuilder()
                .addObject(main)
                .addCommand("create", microservice)
                .build()
                .parse(argv);
        main.run(microservice);
    }

    private void run(CommandMicroservice microservice) throws IOException {
        Yaml yaml = new Yaml(new Constructor(Org.class));
        InputStream inputStream = new FileInputStream("kubersaur.yaml");
        Org org = yaml.load(inputStream);

        MicroserviceGenerator microserviceGenerator = new MicroserviceGenerator(microservice.name, org);
        microserviceGenerator.generate();

        FileWriter writer = new FileWriter("kubersaur.yaml");
        yaml.dump(org, writer);
        writer.flush();
    }

}
