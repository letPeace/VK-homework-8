package enums;

public enum Role {
    MANAGER("manager"),
    GUEST("guest");

    public final String value;
    Role(String value) {
        this.value = value;
    }
}
