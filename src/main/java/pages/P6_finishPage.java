package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.Utility.findWebElement;

public class P6_finishPage {

    private final WebDriver driver;
    private final By thanksMessageElement = By.tagName("h2");

    public P6_finishPage(WebDriver driver) {
        this.driver = driver;
    }


    public boolean checkingVisibilityOfThanksMessage() {

        return findWebElement(driver, thanksMessageElement).isDisplayed();

    }
}
