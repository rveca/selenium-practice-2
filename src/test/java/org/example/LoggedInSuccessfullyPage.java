package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInSuccessfullyPage {

    private WebDriver driver;
    private By heading = By.tagName("h1");

    public LoggedInSuccessfullyPage (WebDriver driver) {
        this.driver = driver;
    }

    public String getHeadingText () {
        return driver.findElement(heading).getText();
    }
}
