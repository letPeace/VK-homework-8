package servers;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.jetbrains.annotations.NotNull;

public final class DefaultServer {

    private static final String HOST = "localhost";
    private static final Integer PORT = 3466;

    public static Server build() {
        return build(HOST, PORT);
    }

    public static Server build(@NotNull String host, @NotNull Integer port) {
        final var server = new Server();
        try (final var serverConnector = new ServerConnector(server, new HttpConnectionFactory())){
            serverConnector.setHost(host);
            serverConnector.setPort(port);
            server.setConnectors(new Connector[]{serverConnector});
            return server;
        }
    }
}
