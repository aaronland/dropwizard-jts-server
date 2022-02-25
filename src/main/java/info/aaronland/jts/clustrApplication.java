package clustr;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class clustrApplication extends Application<clustrConfiguration> {

    public static void main(final String[] args) throws Exception {
        new clustrApplication().run(args);
    }

    @Override
    public String getName() {
        return "clustr";
    }

    @Override
    public void initialize(final Bootstrap<clustrConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final clustrConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
