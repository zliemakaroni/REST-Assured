package Models.enums;

public enum ApiParameters {

    VARIANT("variant"),
    SID_PARAM("SID"),
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENVIROMENT("env"),
    TEST_ID("testId"),
    CONTENT_TYPE("contentType"),
    IMAGE_PNG("image/png"),
    CONTENT("content"),
    PROJECT_ID("projectId");

    private final String param;

    ApiParameters(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return param;
    }
}
