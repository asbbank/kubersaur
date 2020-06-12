package com.darthShana.kubersaur.cli;

import com.beust.jcommander.Parameter;

public class CommandMicroservice {

    @Parameter(description = "action")
    String workload;

    @Parameter(names = "--name")
    String name;

}
