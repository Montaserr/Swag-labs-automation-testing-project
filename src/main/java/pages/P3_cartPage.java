package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.LogsUtils;
import utilities.Utility;

import java.util.List;

public class P3_cartPage {

    static float totalPrice = 0;
    private final WebDriver driver;

    private final By selectedProductsCartElement = By.xpath("//button[.=\"REMOVE\"] //preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final By checkoutButtonElement = By.xpath("//a[contains(@class,\"checkout_button\")]");

    public P3_cartPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getPriceOfCartProducts() {

        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(selectedProductsCartElement);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {

                By productPrice = By.xpath("(//button[.=\"REMOVE\"] //preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String fullPriceText = Utility.getText(driver, productPrice);
                totalPrice += Float.parseFloat(fullPriceText.replace("$", ""));
                //LogsUtils.info("totalPrice in cart page 1th ittiration" + totalPrice);


            }
            LogsUtils.info("totalPrice in cart page = " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {

            LogsUtils.error(e.getMessage());
            LogsUtils.info("totalPrice in cart page = 0 ");
            return "0";

        }


    }

    public boolean comparingTotalPrices(String price) {

        return getPriceOfCartProducts().equals(price);

    }

    public P4_checkoutPage clickingOnCheckoutButton() {

        Utility.clickingOnElement(driver, checkoutButtonElement);
        return new P4_checkoutPage(driver);

    }


}
