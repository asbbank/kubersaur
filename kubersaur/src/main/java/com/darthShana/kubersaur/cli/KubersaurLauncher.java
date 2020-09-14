package com.darthShana.kubersaur.cli;

import com.beust.jcommander.JCommander;
import com.darthShana.kubersaur.model.Org;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;

public class KubersaurLauncher {

    public static void main(String ... argv) throws IOException {
        CreateMicroservice microservice = new CreateMicroservice();
        PrepareMicroservice prepare = new PrepareMicroservice();

        KubersaurLauncher main = new KubersaurLauncher();
        JCommander commander = JCommander.newBuilder()
                .addObject(main)
                .addCommand("create", microservice)
                .addCommand("prepare", prepare)
                .build();

        commander.parse(argv);

        String parsedCommand = commander.getParsedCommand();
        KubersaurLauncher launcher = new KubersaurLauncher();

        if("create".equals(parsedCommand)){
            launcher.launchCreate(microservice);
        }else if("prepare".equals(parsedCommand)){
            launcher.launchPrepare(prepare);
        }


    }

    public void launchCreate(CreateMicroservice microservice) throws IOException {
        Yaml yaml = new Yaml(new Constructor(Org.class));
        InputStream inputStream = new FileInputStream("kubersaur.yaml");
        Org org = yaml.load(inputStream);
        microservice.run(org, yaml);
    }

    public void launchPrepare(PrepareMicroservice prepare) throws IOException {
        Yaml yaml = new Yaml(new Constructor(Org.class));
        InputStream inputStream = new FileInputStream("kubersaur.yaml");
        Org org = yaml.load(inputStream);
        prepare.run(org, yaml);
    }


    public void launchInit(InitialiseOrganisation init) throws IOException {
        Org org = new Org();
        org.setName(init.organisationName());
        org.setPackagePath(init.organisationPackage());
        init.run(org);
    }
}
