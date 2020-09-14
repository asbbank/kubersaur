package com.darthShana.kubersaur.generator.organisation;

import com.darthShana.kubersaur.generator.Generator;
import com.darthShana.kubersaur.generator.core.FileGeneratorBuilder;
import com.darthShana.kubersaur.model.Org;

import java.io.IOException;

public class OrganisationGenerator extends Generator {



    public OrganisationGenerator(Org org) {
        this.org = org;
    }

    public String organisationName(){
        return org.getName();
    }
    public String organisationPackage(){
        return org.getPackagePath();
    }

    @Override
    public void generate() throws IOException {
        new FileGeneratorBuilder("pom.xml")
                .atLocation("./")
                .withTemplate("pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation("code/")
                .withTemplate("code/pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation("code/common")
                .withTemplate("code/common/pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation("code/tiles")
                .withTemplate("code/tiles/pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation("code/composite")
                .withTemplate("code/composite/pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation("code/api")
                .withTemplate("code/api/reactor-pom.mustache")
                .generate(this);

        new FileGeneratorBuilder("pom.xml")
                .atLocation("code/service")
                .withTemplate("code/service/reactor-pom.mustache")
                .generate(this);
    }
}
