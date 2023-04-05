package Utils;

import Configuration.TestData;
import Models.Test;
import Models.enums.ApiMethods;
import Models.enums.ApiParameters;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiUtil {

    public static Response post(ApiMethods method, HashMap<String, String> params) {
        LoggerUtil.getInstance().info("Getting response for method: " + method.toString());

        Response response = given()
                .basePath(method.toString())
                .queryParams(params)
                .contentType(ContentType.JSON)
                .when().post()
                .then()
                .extract().response();

        LoggerUtil.getInstance().info("Response");
        LoggerUtil.getInstance().info("Status code: " + response.getStatusCode());
        LoggerUtil.getInstance().info("Result: ");
        LoggerUtil.getInstance().info(response.asString());
        return response;
    }

    public static Response getTokenResponse() {
        HashMap<String, String> params = new HashMap<>();
        params.put(ApiParameters.VARIANT.toString(), TestData.getVariant());
        return post(ApiMethods.GET_TOKEN, params);
    }

    public static Response getTestListResponse(String projectId) {
        HashMap<String, String> params = new HashMap<>();
        params.put(ApiParameters.PROJECT_ID.toString(), projectId);
        return post(ApiMethods.GET_JSON, params);
    }

    public static Response getCreateTestResponse(String sid, String projectName, Test test) {
        HashMap<String, String> params = new HashMap<>();
        params.put(ApiParameters.SID_PARAM.toString(), sid);
        params.put(ApiParameters.PROJECT_NAME.toString(), projectName);
        params.put(ApiParameters.TEST_NAME.toString(), test.getName());
        params.put(ApiParameters.METHOD_NAME.toString(), test.getMethod());
        params.put(ApiParameters.ENVIROMENT.toString(), "I really don't know what i should past here");
        return post(ApiMethods.CREATE_TEST, params);
    }

    public static void setScreenshot(String recordId) {
        LoggerUtil.getInstance().info("Getting response for method: " + ApiMethods.ATTACHMENT);

        Response response = given()
                .basePath(ApiMethods.ATTACHMENT.toString())
                .formParam(ApiParameters.TEST_ID.toString(), recordId)
                .formParam(ApiParameters.CONTENT.toString(), FileUtil.encodeFileToBase64Binary(FileUtil.getScreenshotFile()))
                .formParam(ApiParameters.CONTENT_TYPE.toString(), (ApiParameters.IMAGE_PNG.toString()))
                .when().post()
                .then()
                .extract().response();

        LoggerUtil.getInstance().info("Response");
        LoggerUtil.getInstance().info("Status code: " + response.getStatusCode());
        LoggerUtil.getInstance().info("Result: ");
        LoggerUtil.getInstance().info(response.asString());
    }

    public static void setLogs(String testId) {
        LoggerUtil.getInstance().info("Getting response for method: " + ApiMethods.PUT_LOG);

        Response response = given()
                .basePath(ApiMethods.PUT_LOG.toString())
                .formParam(ApiParameters.TEST_ID.toString(), testId)
                .formParam(ApiParameters.CONTENT.toString(), FileUtil.getLogAsString(FileUtil.getLogFile()))
                .when().post()
                .then()
                .extract().response();

        LoggerUtil.getInstance().info("Response");
        LoggerUtil.getInstance().info("Status code: " + response.getStatusCode());
        LoggerUtil.getInstance().info("Result: ");
        LoggerUtil.getInstance().info(response.asString());
    }
}
