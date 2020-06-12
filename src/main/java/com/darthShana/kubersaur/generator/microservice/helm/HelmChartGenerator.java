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

    public void generate() throws IOException {
        new File(baseDir).mkdirs();
        new File(baseDir+"/templates").mkdirs();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("platform/helm/charts/Charts.mustache");
        mustache.execute(new FileWriter(baseDir+"/"+"Charts.yaml"), this).flush();
        mustache = mf.compile("platform/helm/charts/values.mustache");
        mustache.execute(new FileWriter(baseDir+"/"+"values.yaml"), this).flush();
    }
}
