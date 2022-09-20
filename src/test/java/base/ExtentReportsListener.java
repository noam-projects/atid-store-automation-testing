package base;

import Utils.Screenshots;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ExtentReportsListener implements ITestListener {


    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        ExtentReporter.test=ExtentReporter.extent.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        ExtentReporter.extent.flush();
    }

    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        try {
            Screenshots.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtentReporter.test.fail("action failed screenshot added");
        ExtentReporter.extent.flush();
    }

    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        ExtentReporter.test.skip("test skipped");
        ExtentReporter.extent.flush();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
