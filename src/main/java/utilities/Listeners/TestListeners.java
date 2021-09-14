package utilities.Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Log;

public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Log.startTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.endTest("PASSED", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.endTest("FAILED", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.endTest("SKIPPED", result.getName());
    }
}
