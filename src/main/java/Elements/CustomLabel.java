package Elements;

import Utils.LoggerUtil;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.HighlightState;
import aquality.selenium.elements.Label;
import org.openqa.selenium.By;

public class CustomLabel extends Label {

    public CustomLabel(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    public boolean waitForDisplayed() {
        LoggerUtil.getInstance().info(String.format("Waiting for label [%s] displayed", this.getName()));
        return this.state().waitForDisplayed();
    }

    public boolean isDisplayed() {
        LoggerUtil.getInstance().info(String.format("Checking that label [%s] displayed", this.getName()));
        return this.state().isDisplayed();
    }

    @Override
    public String getText() {
        String res = this.getText(HighlightState.DEFAULT);
        LoggerUtil.getInstance().info(String.format("Getting text from label [%s] - [%s]", this.getName(), res));
        return res;
    }

    @Override
    public void click() {
        LoggerUtil.getInstance().info(String.format("Clicking the label [%s]", this.getName()));
        this.getJsActions().highlightElement();
        this.doWithRetry(() -> {
            this.getElement().click();
        });
    }
}
