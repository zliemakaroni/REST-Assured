package Models.enums;

public enum ApiMethods {
    GET_TOKEN("token/get"),
    GET_JSON("/test/get/json"),
    CREATE_TEST("test/put"),
    ATTACHMENT("test/put/attachment"),
    PUT_LOG("test/put/log");
    private final String method;

    ApiMethods(String method) {
            this.method = method;
        }

    @Override
    public String toString() {
        return method;
    }
}
