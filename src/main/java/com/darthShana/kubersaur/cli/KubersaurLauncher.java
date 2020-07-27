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
        PrepareMicroservice prepare = new PrepareMicroservice();

        KubersaurLauncher main = new KubersaurLauncher();
        JCommander commander = JCommander.newBuilder()
                .addObject(main)
                .addCommand("create", microservice)
                .addCommand("prepare", prepare)
                .build();

        commander.parse(argv);

        String parsedCommand = commander.getParsedCommand();

        Yaml yaml = new Yaml(new Constructor(Org.class));
        InputStream inputStream = new FileInputStream("kubersaur.yaml");
        Org org = yaml.load(inputStream);

        if("create".equals(parsedCommand)){
            microservice.run(org, yaml);
        }else if("prepare".equals(parsedCommand)){
            prepare.run(org, yaml);
        }


    }



}
