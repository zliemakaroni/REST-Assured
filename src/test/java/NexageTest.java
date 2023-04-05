import Configuration.Config;
import Configuration.TestData;
import PageObjects.AddProjectPopup;
import PageObjects.ProjectPage;
import PageObjects.ProjectsPage;
import PageObjects.TestPage;
import Utils.*;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class NexageTest extends BaseTest {

    @Test
    public void workWithUiAndApi() {

        String token = ApiUtil.getTokenResponse().asString();
        LoggerUtil.getInstance().info(String.format("Checking that token [%s] not empty", token));
        Assert.assertFalse(token.isEmpty(), "Token is empty");

        ProjectsPage projectsPage = new ProjectsPage();
        AqualityServices.getBrowser().goTo(Config.getAuthorizationUrl());
        LoggerUtil.getInstance().info(String.format("Checking that [%s] page is opened", projectsPage.getName()));
        Assert.assertTrue(projectsPage.state().isDisplayed(), String.format("[%s] page is not opened", projectsPage));

        BrowserUtil.addCookie("token", token);
        AqualityServices.getBrowser().refresh();
        LoggerUtil.getInstance().info(String.format("Checking that [%s] variant is displayed", TestData.getVariant()));
        Assert.assertTrue(projectsPage.isVariantDisplayed(TestData.getVariant()), "Correct variant is not displayed!");

        String projectName = TestData.getProject();
        projectsPage.clickProjectByName(projectName);
        ProjectPage nexagePage = new ProjectPage(projectName);
        LoggerUtil.getInstance().info(String.format("Checking that [%s] page is opened", nexagePage.getName()));
        Assert.assertTrue(nexagePage.state().waitForDisplayed(), String.format("%s page is not displayed", projectName));

        List<Models.Test> apiList = JsonUtil.createTestListFromString(
                        ApiUtil.getTestListResponse(TestData.getProjectId()).asString());
        List<Models.Test> guiList = nexagePage.getTestsList();
        LoggerUtil.getInstance().info("Checking that list from GUI is sorted");
        Assert.assertTrue(TestListUtil.isTestListSorted(guiList), "List from GUI isn't sorted by start time");

        LoggerUtil.getInstance().info("Checking that list from API contains all tests from GUI");
        Assert.assertTrue(TestListUtil.isListContainsTestsFromList(apiList, guiList), "List from GUI isn't equals to list from API");

        AqualityServices.getBrowser().getDriver().navigate().back();
        projectsPage.clickAddButton();
        ArrayList<String> tabs2 = new ArrayList<>(AqualityServices.getBrowser().getDriver().getWindowHandles());
        AqualityServices.getBrowser().getDriver().switchTo().window(tabs2.get(1));
        AddProjectPopup addProjectPopup = new AddProjectPopup();
        String newProjectName = RandomUtil.getRandomString(TestData.getRandomLength());
        addProjectPopup.fillProjectName(newProjectName);
        addProjectPopup.clickSubmit();
        LoggerUtil.getInstance().info("Checking that message about saved project is displayed");
        Assert.assertTrue(addProjectPopup.isSuccessDisplayed(), "No message about saved project");

        AqualityServices.getBrowser().getDriver().close();
        AqualityServices.getBrowser().getDriver().switchTo().window(tabs2.get(0));
        AqualityServices.getBrowser().getDriver().navigate().refresh();
        LoggerUtil.getInstance().info("Checking that added project is displayed");
        Assert.assertTrue(projectsPage.isProjectDisplayed(newProjectName), "The added project is not displayed");

        projectsPage.clickProjectByName(newProjectName);
        ProjectPage newProjectPage = new ProjectPage(newProjectName);
        String randomSid = RandomUtil.getRandomString(TestData.getRandomLength());
        ScreenshotUtil.takeScreenshot();
        Models.Test randomTest = RandomUtil.getRandomTest();
        String recordId = ApiUtil.getCreateTestResponse(randomSid, newProjectName, randomTest).asString();
        ApiUtil.setScreenshot(recordId);
        ApiUtil.setLogs(recordId);
        LoggerUtil.getInstance().info("Checking that added test is displayed");
        Assert.assertTrue(
                AqualityServices.getConditionalWait().waitFor(() -> newProjectPage.isTableContainsTest(randomTest.getName())),
                "The added test is not displayed");

        newProjectPage.clickOnTestByName(randomTest.getName());
        TestPage newTestPage = new TestPage(randomTest.getName());
        LoggerUtil.getInstance().info("Checking that logs is displayed");
        Assert.assertTrue(newTestPage.isLogsDisplayed(), "Logs is not displayed");

        LoggerUtil.getInstance().info("Checking that screenshot is displayed");
        Assert.assertTrue(newTestPage.isScreenshotDisplayed(), "Screenshot is not displayed");
    }
}
