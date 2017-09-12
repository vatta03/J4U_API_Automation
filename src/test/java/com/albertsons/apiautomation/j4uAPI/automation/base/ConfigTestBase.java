package com.albertsons.apiautomation.j4uAPI.automation.base;

import com.albertsons.apiautomation.apiTestFramework.constants.FwGlobalConstants;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import com.albertsons.apiautomation.j4uAPI.automation.reportGeneration.ExtentManager;
import com.albertsons.apiautomation.j4uAPI.automation.reportGeneration.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * Created by kedupuganti on 8/18/2017.
 */
public class ConfigTestBase {
    public GlobalConstants globalConstants = new GlobalConstants();
    public GlobalConstants gConstants = new GlobalConstants();
    public String environment = null;
    public String currentAPIGroupName = null;
    public String currentAPIName = null;
    @Parameters({"currentTestEnvironment","apiGroupName", "apiName"})
    @BeforeClass
    public void setupSuite(String environment,String currentAPIGroupName,String currentAPIName) throws Exception {

        this.environment = System.getProperty("environment");
        if(this.environment!=null){    // through jenkins execution
            this.environment = environment;
            this.currentAPIGroupName = currentAPIGroupName;
            this.currentAPIName = currentAPIName;
            GlobalConstants.testEnvironment = this.environment;
            gConstants.currentTestAPIGroupName = this.currentAPIGroupName;
            gConstants.currentTestAPI = this.currentAPIName;
        }else{                                      // through local execution
            GlobalConstants.testEnvironment = environment;
            globalConstants.currentTestAPIGroupName = currentAPIGroupName;
            globalConstants.currentTestAPI = currentAPIName;
            gConstants = globalConstants;
        }

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        ExtentTestManager.startTest(method.getName());
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed" ,result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped", result.getThrowable());
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed" , "Test passed");
        }

        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }



}
