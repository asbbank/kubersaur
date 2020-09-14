package com.darthShana.kubersaur.cli;

import com.darthShana.kubersaur.generator.organisation.OrganisationGenerator;
import com.darthShana.kubersaur.model.Org;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileWriter;
import java.io.IOException;

public class InitialiseOrganisation {


    private String organisationName;
    private String organisationPackage;

    public InitialiseOrganisation(String organisationName, String organisationPackage) {

        this.organisationName = organisationName;
        this.organisationPackage = organisationPackage;
    }

    public String organisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String organisationPackage() {
        return organisationPackage;
    }

    public void setOrganisationPackage(String organisationPackage) {
        this.organisationPackage = organisationPackage;
    }

    public void run(Org org) throws IOException {
        new OrganisationGenerator(org).generate();

        Yaml yaml = new Yaml(new Constructor(Org.class));
        FileWriter writer = new FileWriter("kubersaur.yaml");
        yaml.dump(org, writer);
        writer.flush();
    }


}
