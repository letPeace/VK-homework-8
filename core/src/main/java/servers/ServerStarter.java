package servers;

import enums.Folder;
import guice.GuiceListener;
import login.SecurityHandlerBuilder;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class ServerStarter {

    public static final String LOGIN = "login";

    private final Server server;

    public ServerStarter() {
        this.server = DefaultServer.build();
    }

    public void start() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath(Folder.ROOT.value);

        final String jdbcConfig = getClass().getResource(Folder.CONFIGS_JDBC.value).toExternalForm();
        final JDBCLoginService jdbcLoginService = new JDBCLoginService(LOGIN, jdbcConfig);
        final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(jdbcLoginService);
        securityHandler.setHandler(context);
        server.setHandler(securityHandler);
        server.addBean(jdbcLoginService);

        context.addServlet(HttpServletDispatcher.class, Folder.ROOT.value);
        context.addEventListener(new GuiceListener());

        server.start();
    }

}
