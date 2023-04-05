package PageObjects;

import Elements.CustomLabel;
import Models.Test;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends Form {

    private static final String PROJECT_NAME_TEMPLATE = "//ol[contains(@class, 'breadcrumb')]//li[contains(text(),'%s')]";

    private final CustomLabel tableTests = getElementFactory()
            .getCustomElement(CustomLabel::new, By.xpath("//table[contains(@class,'table')]"), "Tests table");

    private final By rowXpath = By.xpath("//tr[.//td]");

    public ProjectPage(String projectName) {
        super(By.xpath(String.format(PROJECT_NAME_TEMPLATE, projectName)), projectName);
    }

    public List<Test> getTestsList() {

        List<CustomLabel> listFromUI = tableTests.findChildElements(rowXpath, "Item row", CustomLabel.class);
        List<Test> list = new ArrayList<Test>();

        listFromUI.forEach(
                row -> list.add(new Test(
                        row.findChildElement(TestsTableFields.NAME.getLocator(), "name", CustomLabel.class).getText(),
                        row.findChildElement(TestsTableFields.METHOD.getLocator(), "method", CustomLabel.class).getText(),
                        row.findChildElement(TestsTableFields.STATUS.getLocator(), "status", CustomLabel.class).getText(),
                        row.findChildElement(TestsTableFields.START_TIME.getLocator(), "startTime", CustomLabel.class).getText(),
                        row.findChildElement(TestsTableFields.END_TIME.getLocator(), "endTime", CustomLabel.class).getText(),
                        row.findChildElement(TestsTableFields.DURATION.getLocator(), "duration", CustomLabel.class).getText())
        ));
        return list;
    }

    public boolean isTableContainsTest(String name) {
        for(CustomLabel row: getTestsLabelList()) {
            if(row.findChildElement(TestsTableFields.NAME.getLocator(), "name", CustomLabel.class).getText().contains(name)) return true;
        }
        return false;
    }

    private List<CustomLabel> getTestsLabelList() {
        return tableTests.findChildElements(rowXpath, "Item row", CustomLabel.class);
    }

    public void clickOnTestByName(String testName) {
        getTestsLabelList().stream().filter(
                row -> row.findChildElement(TestsTableFields.NAME.getLocator(), "name", CustomLabel.class).getText().equals(testName)
        ).findFirst().get().findChildElement(TestsTableFields.NAME.getLocator(), "name", CustomLabel.class).
                findChildElement(By.xpath("/a"), "Name link", CustomLabel.class).click();
    }

    private enum TestsTableFields {
        NAME("//td[1]"),
        METHOD("//td[2]"),
        STATUS("//td[3]"),
        START_TIME("//td[4]"),
        END_TIME("//td[5]"),
        DURATION("//td[6]");

        private final String xpath;
        TestsTableFields(String xpath) {
            this.xpath = xpath;
        }

        public By getLocator() {
            return By.xpath(xpath);
        }
    }
}
