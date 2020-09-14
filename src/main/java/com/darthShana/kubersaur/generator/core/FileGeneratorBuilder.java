package com.darthShana.kubersaur.generator.core;

import com.darthShana.kubersaur.generator.Generator;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGeneratorBuilder {
    private String sourceFile;
    private String sourceDir;
    private String template;

    public FileGeneratorBuilder(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public FileGeneratorBuilder atLocation(String sourceDir) {
        this.sourceDir = sourceDir;
        return this;
    }

    public FileGeneratorBuilder withTemplate(String template) {
        this.template = template;
        return this;
    }

    public void generate(Generator generator) throws IOException {
        if(!new File(sourceDir).exists()){
            new File(sourceDir).mkdirs();
        }
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(template);
        mustache.execute(new FileWriter(sourceDir+"/"+sourceFile), generator).flush();
    }
}
