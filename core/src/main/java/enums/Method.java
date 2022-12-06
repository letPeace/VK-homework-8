package enums;

public enum Method {
    GET("GET"),
    POST("POST");

    public final String value;
    Method(String value) {
        this.value = value;
    }
}
