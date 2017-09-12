package com.albertsons.apiautomation.j4uAPI.automation.common;

import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import com.albertsons.apiautomation.j4uAPI.automation.reportGeneration.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Created by kedupuganti on 8/18/2017.
 */
public class TestListener extends ConfigTestBase implements ITestListener {
    private AppGeneric appGeneric = new AppGeneric();
    @Override
    public void onTestStart(ITestResult iTestResult) {
        Reporter.log("Test Steps for: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        try {
            appGeneric.generateExtentReport(GlobalConstants.ReportStatus.FAIL.toString(),iTestResult.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //      System.out.println("test method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //      System.out.println("test failed but within success % " + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //      System.out.println("on start of test " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //      System.out.println("on finish of test " + iTestContext.getName());
    }
}
