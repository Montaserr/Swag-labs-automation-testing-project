package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.LogsUtils;
import utilities.Utility;

import java.util.List;
import java.util.Set;

import static utilities.Utility.generalWait;

public class P2_LandingPage {

    static float totalPrice = 0;

    private static List<WebElement> allProducts;
    private static List<WebElement> productsSelected;
    private final By addAllToCartElement = By.xpath("//button[@class]");
    private final By numberOfProductsAddedToCartElement = By.xpath("//span[contains(@class,'shopping_cart_badge')]");
    private final By numberOfProductsAddedElement = By.xpath("//button[.=\"REMOVE\"]");
    private final By cartIconElement = By.xpath("//a[contains(@class,'shopping_cart_link')]");
    private final By selectedProductsElement = By.xpath("//button[.=\"REMOVE\"] //preceding-sibling::div[@class=\"inventory_item_price\"]");

    private final WebDriver driver;

    public P2_LandingPage(WebDriver driver) {
        this.driver = driver;
    }


    public P2_LandingPage addAllProductsToCart() {

        allProducts = driver.findElements(addAllToCartElement);
        for (int i = 1; i <= allProducts.size(); i++) {
            By addAllToCartElement = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickingOnElement(driver, addAllToCartElement);

        }
        return this;

    }

    public String getNumberOfProductsAddedFromCart() {
        try {

            return Utility.getText(driver, numberOfProductsAddedToCartElement);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";

        }
    }

    public String getNumberOfProductsAddedFromDom() {
        try {
            productsSelected = driver.findElements(numberOfProductsAddedElement);
            return String.valueOf(productsSelected.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }

    public boolean compareNumberOfProducts() {
        LogsUtils.info("Number of products added to cart " + getNumberOfProductsAddedFromCart());
        LogsUtils.info("Number of products added to cart from DOM " + getNumberOfProductsAddedFromDom());

        return getNumberOfProductsAddedFromCart().equals(getNumberOfProductsAddedFromDom());

    }

    public P2_LandingPage addRandomProducts(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> numbersGenerated = Utility.generateUniqueRandomNumbers(numberOfProductsNeeded, totalNumberOfProducts);
        for (int random : numbersGenerated) {
            By addAllToCartElement = By.xpath("(//button[@class])[" + random + "]");
            Utility.clickingOnElement(driver, addAllToCartElement);

        }
        return this;

    }

    public P3_cartPage clickingOnCartIcon() {
        Utility.clickingOnElement(driver, cartIconElement);
        LogsUtils.info(" Browser redirected to https://www.saucedemo.com/v1/cart.html");
        return new P3_cartPage(driver);
    }

    public boolean checkingCartPageURL(String expectedURL) {
        try {

            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public String getPriceOfSelectedProducts() {

        try {
            List<WebElement> pricesOfSelectedProducts = driver.findElements(selectedProductsElement);
            for (int i = 1; i <= pricesOfSelectedProducts.size(); i++) {
                By productPrice = By.xpath("(//button[.=\"REMOVE\"] //preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");

                String fullPriceText = Utility.getText(driver, productPrice);
                totalPrice += Float.parseFloat(fullPriceText.replace("$", ""));
                //LogsUtils.info("totalPrice in landing page 1th ittiration" + totalPrice);


            }
            LogsUtils.info("totalPrice in landing page" + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {

            LogsUtils.error(e.getMessage());
            LogsUtils.info("totalPrice in landing page = 0 ");
            return "0";

        }


    }


}
