package PageObjects;

import Elements.CustomButton;
import Elements.CustomLabel;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class ProjectsPage extends Form {

    private static final String LABEL_PROJECTS_XPATH = "//div[contains(text(),'Available projects')]";
    private static final String VARIANT_TEMPLATE = "Version: %s";
    private final CustomLabel lblVariant = AqualityServices.getElementFactory()
            .getCustomElement(CustomLabel::new, By.xpath("//p[contains(@class, 'footer-text')]//span"), "Variant");

    private final CustomButton btnAdd = AqualityServices.getElementFactory()
            .getCustomElement(CustomButton::new, By.xpath("//a[@href='addProject']"), "Add project");
    private final CustomLabel tableProjects = AqualityServices.getElementFactory()
            .getCustomElement(CustomLabel::new, By.xpath("//div[contains(@class, 'list-group')]"), "Projects table");

    private final By rowXpath = By.xpath("//a");

    public ProjectsPage() {
        super(By.xpath(LABEL_PROJECTS_XPATH), "Projects");
    }

    public void clickAddButton() {
        btnAdd.click();
    }

    public boolean isProjectDisplayed(String projectName) {
        return !getProjectsList().stream().filter(
                row -> row.getText().equals(projectName)
        ).toList().isEmpty();
    }

    public void clickProjectByName(String projectName) {
        getProjectsList().stream().filter(
                row -> row.getText().equals(projectName)
        ).findFirst().get().click();
    }

    public boolean isVariantDisplayed(String variant) {
        return lblVariant.getText().equals(String.format(VARIANT_TEMPLATE, variant));
    }

    private List<CustomLabel> getProjectsList() {
        return tableProjects.findChildElements(rowXpath, "Item row", CustomLabel.class);
    }
}
