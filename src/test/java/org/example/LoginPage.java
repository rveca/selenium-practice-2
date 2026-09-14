package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitButton = By.id("submit");
    private By errorMessage = By.id("error");

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goTo () {
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    public void enterUsername (String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword (String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit () {
        driver.findElement(submitButton).click();
    }

    public String getErrorText () {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return error.getText();
    }

    public void login (String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }
}
