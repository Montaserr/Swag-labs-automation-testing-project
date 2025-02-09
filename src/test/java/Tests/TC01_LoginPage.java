package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P1_LoginPage;
import utilities.DataUtils;
import utilities.LogsUtils;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static utilities.DataUtils.getPropertyValue;

public class TC01_LoginPage {

    private final String USERNAME = DataUtils.getJsonData("validlogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validlogin", "password");

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Browser is opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "Base_URL"));
        LogsUtils.info("Browser is redirected to https://www.saucedemo.com/v1/ ");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void validLogin_TC0() throws IOException {
        new P1_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickingOnLogin();

        Assert.assertTrue(new P1_LoginPage(getDriver())
                .assertLoginTC(getPropertyValue("environment", "LandingPage_URL")));
    }


    @AfterMethod
    public void quit() {
        quitDriver();


    }

}
