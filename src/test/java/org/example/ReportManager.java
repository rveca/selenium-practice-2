package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReportManager {

    private static ExtentReports extent;
    private static Map<String, ExtentTest> testMap = new HashMap<>();

    public static ExtentReports getInstance () {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/report.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static ExtentTest createTest (String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testMap.put(testName, test);
        return test;
    }

    public static ExtentTest getTest (String testName) {
        return testMap.get(testName);
    }

    public static String takeScreenshot (WebDriver driver, String testName) throws Exception {
        String relativePath = "screenshots/" + testName + ".png";
        String fullPath = "test-output/" + relativePath;
        Files.createDirectories(Paths.get(fullPath).getParent());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new File(fullPath).toPath());
        return relativePath;
    }
}
