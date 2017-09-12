package com.albertsons.apiautomation.j4uAPI.automation.reportGeneration;

import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kedupuganti on 8/18/2017.
 */
public class ExtentTestManager extends ConfigTestBase {

    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }


    /**
     *
     * @param screenShotName
     * @return
     * @throws Exception
     */
    /*
    public static String getScreenShotByExtent(String screenShotName) throws Exception {
        File srcFile,destFile = null;
        try{
            RemoteWebDriver remoteWebDriver = getDriver();
            srcFile = ((TakesScreenshot) remoteWebDriver).getScreenshotAs(OutputType.FILE);
            String imagesLocation = System.getProperty("user.dir") +"\\ExtentScreenshots\\";
            String dest = imagesLocation + screenShotName+".png";
            destFile = new File(dest);
            FileUtils.copyFile(srcFile, destFile);
        }catch(Exception exception){
            return null;
        }
        return destFile.getAbsolutePath();

    } */


}
