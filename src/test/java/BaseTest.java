import Configuration.Config;
import Utils.LoggerUtil;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = Config.getApiUrl();
        LoggerUtil.addMessagesAppender();
    }

    @AfterMethod
    public void quit() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
        Logger.getInstance().removeAppender(LoggerUtil.getAppenderInstance());
    }
}
