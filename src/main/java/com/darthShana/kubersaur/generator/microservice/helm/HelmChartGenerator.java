package com.darthShana.kubersaur.generator.microservice.helm;

import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelmChartGenerator {
    private final String microserviceName;
    private final Org org;
    private final String baseDir;

    public HelmChartGenerator(String microserviceName, Org org) {
        this.microserviceName = microserviceName;
        this.org = org;
        this.baseDir = "platform/helm/"+org.getName()+"/charts/"+microserviceName;
    }

    public String microserviceName(){
        return microserviceName;
    }

    public void generate() throws IOException {
        new File(baseDir).mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("platform/helm/charts/Chart.mustache");
        mustache.execute(new FileWriter(baseDir+"/"+"Charts.yaml"), this).flush();
        mustache = mf.compile("platform/helm/charts/values.mustache");
        mustache.execute(new FileWriter(baseDir+"/"+"values.yaml"), this).flush();

        new File(baseDir+"/templates").mkdirs();
        mustache = mf.compile("platform/helm/charts/templates/helpers.mustache");
        mustache.execute(new FileWriter(baseDir+"/templates/"+"_helpers.tpl"), this).flush();
        mustache = mf.compile("platform/helm/charts/templates/application.mustache");
        mustache.execute(new FileWriter(baseDir+"/templates/"+"application.yaml"), this).flush();
        mustache = mf.compile("platform/helm/charts/templates/config.mustache");
        mustache.execute(new FileWriter(baseDir+"/templates/"+"config.yaml"), this).flush();
    }
}
