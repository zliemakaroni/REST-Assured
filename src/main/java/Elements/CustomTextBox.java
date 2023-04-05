package Elements;

import Utils.LoggerUtil;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.TextBox;
import org.openqa.selenium.By;

public class CustomTextBox extends TextBox {

    public CustomTextBox(By locator, String name, ElementState state) {
        super(locator, name, state);
    }
    @Override
    public void clearAndType(String value) {
        LoggerUtil.getInstance().info(String.format("Clearing and typing [%s] string in text box [%s]", value, this.getName()));
        this.getJsActions().highlightElement();
        this.doWithRetry(() -> {
            this.getElement().clear();
            this.getElement().sendKeys(value);
        });
    }
}
