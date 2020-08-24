package org.kubersaur.codegen.implementation;

import com.darthShana.kubersaur.model.Org;
import org.kubersaur.codegen.Language;

public interface CodegenConfig {

    String getName();

    Language getLanguage();

    void init(String name, String implementationBaseDirectory, String templateDir, Org org);


}
