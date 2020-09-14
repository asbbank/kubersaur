package org.kubersaur.plugin;

import com.darthShana.kubersaur.cli.CreateMicroservice;
import com.darthShana.kubersaur.cli.KubersaurLauncher;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.kubersaur.codegen.Language;

import java.io.IOException;
import java.util.HashSet;

@Mojo(name = "generate-microservice",
        defaultPhase = LifecyclePhase.INITIALIZE,
        requiresDependencyCollection = ResolutionScope.NONE,
        requiresDependencyResolution = ResolutionScope.NONE,
        requiresProject = false,
        threadSafe = true)
public class KubersaurLauncherMojo extends AbstractMojo {

    @Parameter(property = "name")
    String name;

    @Parameter(property = "language")
    String language;

    @Parameter(property = "generators")
    String[] generators;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info( "Hello, generate-microservice." );

        CreateMicroservice createMicroservice = new CreateMicroservice();
        createMicroservice.setName(name);
        createMicroservice.setLanguage(Language.valueOf(language));
        createMicroservice.setGeneratorOverrides(new HashSet<>(Arrays.asList(generators)));

        KubersaurLauncher launcher = new KubersaurLauncher();
        try {
            launcher.launchCreate(createMicroservice);
        } catch (IOException e) {
            getLog().error(e);
            throw new MojoExecutionException(e.getMessage());
        }

    }
}
