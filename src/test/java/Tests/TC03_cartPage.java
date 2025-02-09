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
import pages.P3_cartPage;
import utilities.DataUtils;
import utilities.LogsUtils;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC03_cartPage {

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
    public void comparingPricesTC() throws IOException {
        String totalPrice = new P1_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickingOnLogin()
                .addRandomProducts(3, 6)
                .getPriceOfSelectedProducts();
        new P2_LandingPage(getDriver())
                .clickingOnCartIcon();

        Assert.assertTrue(new P3_cartPage(getDriver()).comparingTotalPrices(totalPrice));


    }

    @AfterMethod
    public void quit() {
        quitDriver();


    }

}
