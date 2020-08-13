package com.darthShana.kubersaur.generator;

import com.darthShana.kubersaur.model.Microservice;
import com.darthShana.kubersaur.model.Org;
import com.google.common.base.CaseFormat;

import java.io.IOException;
import java.util.List;

public abstract class Generator {

    protected final Org org;
    protected final String microserviceName;

    public Generator(Org org, String microserviceName){
        this.org = org;
        this.microserviceName = microserviceName;
    }

    public String organisationPackage() {
        return org.getPackagePath();
    }

    public String organisationName() {
        return org.getName();
    }

    public String microserviceName() {
        return microserviceName.replace("-", "_");
    }

    public String microserviceNameCamelCase(){
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, microserviceName);
    }

    public List<Microservice> microservices(){
        return org.getMicroservices();
    }

    public abstract void generate() throws IOException;

}
