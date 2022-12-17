package enums;

public enum ExceptionMessage {
    NO_PRODUCTS("No such products!");

    public final String value;
    ExceptionMessage(String value) {
        this.value = value;
    }
}
