package Configuration;

import Utils.JsonUtil;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class Config {
    private static final ISettingsFile CONFIG = new JsonSettingsFile("config.json");
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public static String getApiUrl() {
        return  JsonUtil.getStringFromFile("apiUrl", CONFIG);
    }

    public static String getAuthorizationUrl() {
        return  String.format(JsonUtil.getStringFromFile("authorizationUrl", CONFIG), LOGIN, PASSWORD);
    }

    public static String getScreenshotName() {
        return  JsonUtil.getStringFromFile("screenshotName", CONFIG);
    }

    public static String getLogName() {
        return JsonUtil.getStringFromFile("logName", CONFIG);
    }
}
