package Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.LogsUtils;

public class ITestResultListenerClass implements ITestListener {

    public void onTestStart(ITestResult result) {
        LogsUtils.info("TC" + result.getName() + "started");
    }

    public void onTestSuccess(ITestResult result) {

        LogsUtils.info("TC" + result.getName() + "passed");
    }

    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("TC" + result.getName() + "skipped");
    }

}
