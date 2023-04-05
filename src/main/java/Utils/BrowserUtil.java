package Utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.Cookie;

public class BrowserUtil {

    public static void addCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
    }
}
