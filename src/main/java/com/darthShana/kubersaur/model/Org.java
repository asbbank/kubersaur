package com.darthShana.kubersaur.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Org {

    private String name;
    private String packagePath;
    private List<Microservice> microservices = new ArrayList<>();

    private List<String> keywords = Arrays.asList("service", "api", " ");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Microservice> getMicroservices() {
        return microservices;
    }

    public void setMicroservices(List<Microservice> microservices) {
        if(microservices!=null){
            this.microservices = microservices;
        }
    }

    public String getPackagePathDirs() {
        return packagePath.replace(".", "/")+"/";
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public void addMicroservice(Microservice microservice) {
        if(microservices.stream().map(m -> m.getName()).anyMatch(n -> n.equals(microservice.getName()))){
            throw new IllegalArgumentException("duplicate microservice found");
        }
        if(keywords.stream().anyMatch(key -> microservice.getName().toLowerCase().contains(key))){
            throw new IllegalArgumentException("microservice can not contain reserved key words:["+keywords+"]");
        }

        microservices.add(microservice);
    }

    public String getPackagePath() {
        return packagePath;
    }
}
