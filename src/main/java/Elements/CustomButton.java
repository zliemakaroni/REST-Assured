package Elements;

import Utils.LoggerUtil;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Button;
import org.openqa.selenium.By;

public class CustomButton extends Button {

    public CustomButton(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    @Override
    public void click() {
        LoggerUtil.getInstance().info(String.format("Clicking the button [%s]", this.getName()));
        this.getJsActions().highlightElement();
        this.doWithRetry(() -> {
            this.getElement().click();
        });
    }
}
