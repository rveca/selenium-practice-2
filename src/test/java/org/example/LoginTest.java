package org.example;

import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ReportWatcher.class)
public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    LoggedInSuccessfullyPage loggedInSuccessfullyPage;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        loggedInSuccessfullyPage = new LoggedInSuccessfullyPage(driver);
        ReportManager.createTest(testInfo.getDisplayName());
    }

    //@AfterEach
    //void tearDown() {
    //    driver.quit();
    //}

    @AfterAll
    static void report() {
        ReportManager.getInstance().flush();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Test
    void loginWithValidCredentials() {
        loginPage.login("student", "Password123");
        String heading = loggedInSuccessfullyPage.getHeadingText();
        assertTrue(heading.contains("Logged In Successfully"));
    }

    @Test
    void loginWithInvalidUsername() {
        loginPage.login("wronguser", "Password123");
        assertTrue(loginPage.getErrorText().contains("invalid"));
    }
}