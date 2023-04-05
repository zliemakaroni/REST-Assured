package PageObjects;

import Elements.CustomLabel;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TestPage extends Form {

    private static final String TEST_NAME_TEMPLATE = "//div[contains(@class, 'list-group-item')]" +
            "//h4[contains(text(), 'Test name')]//following-sibling::p[contains(test(), '%s')]";

    private final CustomLabel lblLogs = getElementFactory()
            .getCustomElement(CustomLabel::new, By.xpath("//div[contains(text(), 'Logs')]//following-sibling::table//td"),
                    "Logs", ElementState.EXISTS_IN_ANY_STATE);

    private final CustomLabel lblImage = getElementFactory()
            .getCustomElement(CustomLabel::new, By.xpath("//div[contains(text(), 'Attachments')]//following-sibling::table//td//img"),
                    "Attached image", ElementState.EXISTS_IN_ANY_STATE);

    public TestPage(String testName) {
        super(By.xpath(String.format(TEST_NAME_TEMPLATE, testName)), testName);
    }

    public boolean isLogsDisplayed() {
        return !lblLogs.getText().isEmpty();
    }

    public boolean isScreenshotDisplayed() {
        return lblImage.isDisplayed();
    }
}
