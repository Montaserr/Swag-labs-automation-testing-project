package Listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utilities.LogsUtils;
import utilities.Utility;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }


    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogsUtils.info("TC" + testResult.getName() + "failed");
            Utility.takeingScreenshot(getDriver(), testResult.getName());

        }
    }


}
