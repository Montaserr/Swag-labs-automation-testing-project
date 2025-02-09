package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utility;

public class P1_LoginPage {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginbutton = By.id("login-button");
    private final WebDriver driver;

    public P1_LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public P1_LoginPage enterUsername(String usernametext) {

        Utility.sendData(driver, username, usernametext);
        return this;

    }

    public P1_LoginPage enterPassword(String passwordtext) {

        Utility.sendData(driver, password, passwordtext);
        return this;

    }

    public P2_LandingPage clickingOnLogin() {

        Utility.clickingOnElement(driver, loginbutton);
        return new P2_LandingPage(driver);

    }

    public boolean assertLoginTC(String expectedvalue) {

        return driver.getCurrentUrl().equals(expectedvalue);
    }
}
