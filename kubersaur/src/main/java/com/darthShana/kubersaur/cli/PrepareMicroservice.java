package com.darthShana.kubersaur.cli;

import com.beust.jcommander.Parameter;
import com.darthShana.kubersaur.model.Org;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PrepareMicroservice implements RunnableCommand{

    @Parameter(description = "action")
    String toPrepare;

    @Parameter(names = "--manifest", required = true)
    String manifestFile;

    List<MicroserviceRelease> microservices = new ArrayList<>();
    private Org org;

    String orgName(){
        return org.getName();
    }


    public void run(Org org, Yaml yaml) throws IOException {
        this.org = org;
        String content = new String(Files.readAllBytes(Paths.get(manifestFile)));

        JSONArray obj = new JSONArray(content);
        for (Iterator it = obj.iterator();it.hasNext();){
            JSONObject proj = (JSONObject) it.next();
            String fullImageName = proj.getString("fullImageName");
            fullImageName = fullImageName.substring(fullImageName.lastIndexOf("/")+1);
            String[] split = fullImageName.split(":");
            microservices.add(new MicroserviceRelease(split[0], split[1]));
        }

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("release/release.mustache");
        mustache.execute(new PrintWriter(System.out, true), this).flush();
    }

    class MicroserviceRelease{

        final String microservice;
        final String version;

        public MicroserviceRelease(String microservice, String version){
            this.microservice = microservice;
            this.version = version;
        }

    }
}
