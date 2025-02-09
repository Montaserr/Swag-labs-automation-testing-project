package Tests;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.P1_LoginPage;
import pages.P5_checkoutOverView;
import utilities.DataUtils;
import utilities.LogsUtils;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static utilities.Utility.getTimestamp;


@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC05_checkoutOverView {


    private final String USERNAME = DataUtils.getJsonData("validlogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validlogin", "password");
    private final String FIRSTNAME = DataUtils.getJsonData("checkoutInformation", "firstName") + "-" + getTimestamp();
    private final String LASTNAME = DataUtils.getJsonData("checkoutInformation", "lastName") + "-" + getTimestamp();
    private final String POSTALCODE = new Faker().number().digits(5);

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("Browser is opened");
        getDriver().get(DataUtils.getPropertyValue("environment", "Base_URL"));
        LogsUtils.info("Browser is redirected to https://www.saucedemo.com/v1/ ");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void checkingTotalTC() throws IOException {
        new P1_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickingOnLogin()
                .addRandomProducts(3, 6)
                .clickingOnCartIcon()
                .clickingOnCheckoutButton()
                .fillInforamtion(FIRSTNAME, LASTNAME, POSTALCODE)
                .clickingOnContinue();


        Assert.assertTrue(new P5_checkoutOverView(getDriver()).compareTotals());


    }

    @AfterMethod
    public void quit() {
        quitDriver();


    }

}
