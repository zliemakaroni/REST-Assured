package Utils;

import Configuration.Config;
import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    private static final String defaultScreenshot = ScreenshotUtil.class.getClassLoader().getResource(
            Config.getScreenshotName()).toString().substring(5);

    public static void takeScreenshot() {
        File screenshotFile = ((TakesScreenshot) AqualityServices.getBrowser().getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(defaultScreenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDefaultScreenshot() {
        return defaultScreenshot;
    }

}
