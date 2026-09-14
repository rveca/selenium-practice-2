package org.example;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class ReportWatcher implements TestWatcher {

    @Override
    public void testSuccessful (ExtensionContext context) {
        String testName = context.getDisplayName();
        ReportManager.getTest(testName).pass("Test passed");
        quitDriver(context);
    }

    @Override
    public void testFailed (ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        ReportManager.getTest(testName).fail("Test failed: " + cause.getMessage());
        LoginTest testInstance = (LoginTest) context.getRequiredTestInstance();
        WebDriver driver = testInstance.getDriver();
        try {
            ReportManager.getTest(testName).addScreenCaptureFromPath(ReportManager.takeScreenshot(driver, testName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            quitDriver(context);
        }
    }

    private void quitDriver (ExtensionContext context) {
        LoginTest testInstance = (LoginTest) context.getRequiredTestInstance();
        testInstance.getDriver().quit();
    }
}
