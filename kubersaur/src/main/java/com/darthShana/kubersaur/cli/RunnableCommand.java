package com.darthShana.kubersaur.cli;

import com.darthShana.kubersaur.model.Org;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;

public interface RunnableCommand {

    void run(Org org, Yaml yaml) throws IOException;

}
