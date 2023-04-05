package Configuration;

import Utils.JsonUtil;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestData {

    private static final ISettingsFile TEST_DATA = new JsonSettingsFile("testData.json");

    public static String getVariant() {
        return JsonUtil.getStringFromFile("variant", TEST_DATA);
    }

    public static String getProject() {
        return JsonUtil.getStringFromFile("project", TEST_DATA);
    }

    public static String getProjectId() {
        return JsonUtil.getStringFromFile("projectId", TEST_DATA);
    }

    public static int getRandomLength() {
        return JsonUtil.getIntFromFile("randomLength", TEST_DATA);
    }
}
