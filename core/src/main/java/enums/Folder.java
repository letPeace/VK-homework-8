package enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Folder {
    STATIC(Path.STATIC),
    GUEST(Path.GUEST),
    DELETE(Path.DELETE),
    MANAGER(Path.MANAGER),
    MANAGER_DELETE(MANAGER, MANAGER, DELETE),
    INFO(Path.INFO),
    STATIC_INFO(STATIC, INFO),
    ROOT("/"),
    CONFIGS_JDBC("/configs/jdbc_config"),
    DB_MIGRATIONS("db/migrations");

    public final String value;
    Folder(Path path) {
        this.value = "/"+path.value;
    }
    Folder(String value) {
        this.value = value;
    }
    Folder(Folder... folders){
        this(Arrays.stream(folders).map(folder -> folder.value).collect(Collectors.joining()));
    }
}
