package enums;

public enum Path {
    STATIC("static"),
    GUEST("guest"),
    MANAGER("manager"),
    INFO("info"),
    DELETE("delete");

    public final String value;
    Path(String value) {
        this.value = value;
    }
}
