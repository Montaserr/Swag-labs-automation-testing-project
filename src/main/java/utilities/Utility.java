package utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {

    private static final String SCREENSHOTS_PATH = "TestOutputs/Screenshots/";

    public static void clickingOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }

    public static void sendData(WebDriver driver, By locator, String data) {

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);

    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();


    }

    public static WebDriverWait generalWait(WebDriver driver) {

        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void scroling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("argumnets[0].scrollIntoView();", findWebElement(driver, locator));

    }

    public static WebElement findWebElement(WebDriver driver, By locator) {

        return driver.findElement(locator);
    }

    public static String getTimestamp() {

        return new SimpleDateFormat("yyyy-MM-dd-h-ssa").format(new Date());
    }

    public static void takeingScreenshot(WebDriver driver, String screenshotName) {

        try {
            // capture screenshot using getScreenshotAs
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            //FileUtils.copyFile(screenshotSrc, screenshotFile);
            //Attach the screenshot to allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);

    }

    public static int generateRandomNumber(int upperBound) {

        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> generateUniqueRandomNumbers(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> numbersGenerated = new HashSet<>();
        while (numbersGenerated.size() < numberOfProductsNeeded) {

            numbersGenerated.add(generateRandomNumber(totalNumberOfProducts));
        }
        return numbersGenerated;


    }

    public static boolean verifyURL(WebDriver driver, String expectedURL) {

        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
        return true;


    }
}
