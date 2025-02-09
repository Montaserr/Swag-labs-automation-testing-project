package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utility;


public class P4_checkoutPage {


    private final WebDriver driver;

    private final By firstNameElement = By.id("first-name");
    private final By lastNameElement = By.id("last-name");
    private final By postalCodeElement = By.id("postal-code");
    private final By continueElement = By.xpath("//input[contains(@class,\"cart_button\")]");
    private final By cancelElement = By.xpath("//a[contains(@class,\"cart_cancel_link\")]");


    public P4_checkoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P4_checkoutPage fillInforamtion(String fName, String lName, String postalCode) {

        Utility.sendData(driver, firstNameElement, fName);
        Utility.sendData(driver, lastNameElement, lName);
        Utility.sendData(driver, postalCodeElement, postalCode);
        return this;

    }

    public P5_checkoutOverView clickingOnContinue() {

        Utility.clickingOnElement(driver, continueElement);

        return new P5_checkoutOverView(driver);

    }


}
