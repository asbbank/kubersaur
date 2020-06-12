package com.darthShana.kubersaur.generator;

import java.io.IOException;
import java.io.StringWriter;

public interface Generator {

    String organisationPackage();
    String organisationName();

    void generate() throws IOException;

}
