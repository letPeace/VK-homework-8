package enums;

public enum Schema {
    PUBLIC("public"),
    CORE("core"),
    SECURITY("security");

    public final String value;
    Schema(String value) {
        this.value = value;
    }
}
