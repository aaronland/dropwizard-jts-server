package jts;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.dropwizard.forms.MultiPartBundle;

public class JTSApplication extends Application<JTSConfiguration> {

    public static void main(final String[] args) throws Exception {
        new JTSApplication().run(args);
    }

    @Override
    public String getName() {
        return "jts-server";
    }

    @Override
    public void initialize(final Bootstrap<JTSConfiguration> bootstrap) {

	bootstrap.addBundle(new MultiPartBundle());	
    }

    @Override
    public void run(final JTSConfiguration configuration,
                    final Environment env) {

        env.jersey().register(new FixGeometryResource());
        env.jersey().register(new ConcaveHullResource());		
    }

}
