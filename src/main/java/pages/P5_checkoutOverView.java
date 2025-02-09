package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LogsUtils;
import utilities.Utility;

import static utilities.Utility.getText;

public class P5_checkoutOverView {
    private final WebDriver driver;

    private final By subTotalElement = By.cssSelector("div[class=\"summary_subtotal_label\"]");
    private final By taxElement = By.cssSelector("div[class=\"summary_tax_label\"]");
    private final By totalElement = By.cssSelector("div[class=\"summary_total_label\"]");
    private final By finishElement = By.cssSelector("a.cart_button");


    public P5_checkoutOverView(WebDriver driver) {
        this.driver = driver;
    }

    public float getSubTotal() {
        LogsUtils.info("Subtotal prices = " + (getText(driver, subTotalElement).replace("Item total: $", "")));

        return Float.parseFloat(getText(driver, subTotalElement).replace("Item total: $", ""));


    }

    public float getTax() {

        LogsUtils.info("Tax = " + (getText(driver, taxElement).replace("Tax: $", "")));
        return Float.parseFloat(getText(driver, taxElement).replace("Tax: $", ""));


    }

    public float getActualTotal() {

        LogsUtils.info("Actual total prices" + (getText(driver, totalElement).replace("Total: $", "")));


        return Float.parseFloat(getText(driver, totalElement).replace("Total: $", ""));


    }

    public String getCalculatedTotal() {

        LogsUtils.info("Calculated total = " + (getSubTotal() + getTax()));

        return String.valueOf(getSubTotal() + getTax());


    }

    public boolean compareTotals() {

        return getCalculatedTotal().equals(String.valueOf(getActualTotal()));


    }

    public P6_finishPage clickingOnfinish() {

        Utility.clickingOnElement(driver, finishElement);
        return new P6_finishPage(driver);

    }


}
