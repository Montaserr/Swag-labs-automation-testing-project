package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.P1_LoginPage;
import pages.P2_LandingPage;
import utilities.DataUtils;
import utilities.LogsUtils;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static utilities.Utility.verifyURL;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_landingPage {


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
    public void addAllProducts() throws IOException {
        new P1_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickingOnLogin()
                .addAllProductsToCart();

        Assert.assertTrue(new P2_LandingPage(getDriver())
                .compareNumberOfProducts());
    }

    @Test
    public void addRandomProducts() {
        new P1_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickingOnLogin()
                .addRandomProducts(4, 6);

        Assert.assertTrue(new P2_LandingPage(getDriver())
                .compareNumberOfProducts());


    }

    @Test
    public void clickingOnCartIcon() throws IOException {
        new P1_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickingOnLogin()
                .clickingOnCartIcon();

        Assert.assertTrue(verifyURL(getDriver(), DataUtils.getPropertyValue("environment", "cartURL")));


    }

    @AfterMethod
    public void quit() {
        quitDriver();


    }

}
