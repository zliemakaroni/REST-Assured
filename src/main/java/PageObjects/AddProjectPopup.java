package PageObjects;

import Elements.CustomButton;
import Elements.CustomLabel;
import Elements.CustomTextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectPopup extends Form {

    private final CustomTextBox txbProjectName = getElementFactory()
            .getCustomElement(CustomTextBox::new, By.xpath("//input[@id='projectName']"), "Project Name");
    private final CustomButton btnSubmit = getElementFactory()
            .getCustomElement(CustomButton::new, By.xpath("//button[@type='submit']"), "Submit");
    private final CustomLabel lblSuccess = getElementFactory()
            .getCustomElement(CustomLabel::new, By.xpath("//div[contains(@class, 'alert-success')]"), "Success");

    public AddProjectPopup() {
        super(By.xpath("//div[@class='modal-header']"), "Add project");
    }

   public void fillProjectName(String projectName) {
       txbProjectName.clearAndType(projectName);
   }

   public void clickSubmit() {
        btnSubmit.click();
   }

   public boolean isSuccessDisplayed() {
        return lblSuccess.waitForDisplayed();
   }
}
