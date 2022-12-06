package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import javax.servlet.ServletContext;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import providers.ObjectMapperProvider;
import rest.GuestREST;
import rest.ManagerREST;
import rest.StaticREST;

import java.util.Collections;
import java.util.List;

public class GuiceListener extends GuiceResteasyBootstrapServletContextListener {

    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        return Collections.singletonList(new GuiceModule());
    }

    private static final class GuiceModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(ObjectMapperProvider.class).toInstance(new ObjectMapperProvider());
            bind(ManagerREST.class);
            bind(GuestREST.class);
            bind(StaticREST.class);
        }
    }

}
