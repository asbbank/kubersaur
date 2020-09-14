package org.kubersaur.plugin;

import com.darthShana.kubersaur.cli.InitialiseOrganisation;
import com.darthShana.kubersaur.cli.KubersaurLauncher;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.IOException;

@Mojo(name = "init-organisation",
        defaultPhase = LifecyclePhase.INITIALIZE,
        requiresDependencyCollection = ResolutionScope.NONE,
        requiresDependencyResolution = ResolutionScope.NONE,
        requiresProject = false,
        threadSafe = true)

public class KubersaurOrganisationMojo extends AbstractMojo {

    @Parameter(property = "name")
    String organisationName;

    @Parameter(property = "package")
    String organisationPackage;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info( "Hello, init-organisation." );

        InitialiseOrganisation init = new InitialiseOrganisation(organisationName, organisationPackage);
        KubersaurLauncher launcher = new KubersaurLauncher();
        try {
            launcher.launchInit(init);
        } catch (IOException e) {
            getLog().error(e);
            throw new MojoExecutionException(e.getMessage());
        }
    }
}
