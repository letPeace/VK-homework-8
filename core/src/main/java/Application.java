import database.MigrationsInitializer;
import servers.ServerStarter;

public class Application {

    public static void main(String[] args) throws Exception {
        MigrationsInitializer.initialize();
        var serverStarter = new ServerStarter();
        serverStarter.start();
    }

}
