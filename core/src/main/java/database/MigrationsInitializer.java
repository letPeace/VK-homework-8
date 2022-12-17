package database;

import enums.Folder;
import enums.Schema;
import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;

import java.awt.event.FocusAdapter;

public class MigrationsInitializer {

    private static final @NotNull JDBCCredentials CREDENTIALS = JDBCCredentials.DEFAULT_CREDENTIALS;

    public static void initialize() {
        initialize(CREDENTIALS);
    }

    public static void initialize(@NotNull JDBCCredentials jdbcCredentials){
        final Flyway flyway = Flyway.configure()
                .dataSource(
                        jdbcCredentials.url(),
                        jdbcCredentials.login(),
                        jdbcCredentials.password()
                )
                .schemas(Schema.PUBLIC.value, Schema.CORE.value, Schema.SECURITY.value)
                .cleanDisabled(false)
                .locations(Folder.DB_MIGRATIONS.value)
                .load();
        flyway.clean();
        flyway.migrate();
    }

}
