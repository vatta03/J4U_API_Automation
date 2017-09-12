package com.albertsons.apiautomation.j4uAPI.automation.common;

import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import com.albertsons.apiautomation.j4uAPI.automation.reportGeneration.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Reporter;

/**
 * Created by Kiran on 8/19/2017.
 */
public class AppGeneric {

    public GlobalConstants globalConstants = new GlobalConstants();
    public AppGeneric(){

    }

    /**
     * Description:
     * @param testStepName
     * @param testStepPassDetails
     * @param testStepFailDetails
     * @throws Exception
     */
    public void assignDetails(String testStepName,String testStepPassDetails,String testStepFailDetails) throws Exception {
        globalConstants.stepDescription = testStepName;
        globalConstants.stepPassActual = testStepPassDetails;
        globalConstants.stepFailActual = testStepFailDetails;
    }

    /**
     * Description:
     * @param testStepStatus
     * @throws Exception
     * Comments:
     * */
    public void generateExtentReport(String testStepStatus,String testName) throws Exception{

        if (testStepStatus.equalsIgnoreCase("Pass")){
            ExtentTestManager.getTest().log(LogStatus.PASS, globalConstants.stepDescription,globalConstants.stepPassActual);
            Reporter.log(globalConstants.stepDescription + "@@@" + globalConstants.stepPassActual);
        } else if(testStepStatus.equalsIgnoreCase("Fail")){
            // String screenShotPath = getScreenShotByExtent(testName+"_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            //ExtentTestManager.getTest().log(LogStatus.FAIL,GlobalConstants.stepDescription,GlobalConstants.stepFailActual+ExtentTestManager.getTest().addBase64ScreenShot(screenShotPath));
            ExtentTestManager.getTest().log(LogStatus.FAIL,globalConstants.stepDescription,globalConstants.stepFailActual);
        }else if(testStepStatus.equalsIgnoreCase("Info")){
            ExtentTestManager.getTest().log(LogStatus.INFO,globalConstants.stepDescription,globalConstants.stepPassActual);
            Reporter.log(globalConstants.stepDescription + "@@@" + globalConstants.stepPassActual);
        }else if(testStepStatus.equalsIgnoreCase("Skip")){
            ExtentTestManager.getTest().log(LogStatus.SKIP,globalConstants.stepDescription,globalConstants.stepPassActual);
            Reporter.log(globalConstants.stepDescription + "@@@" + globalConstants.stepPassActual);
        }

    }

    /***
     * Description: Get Host System OS Name
     * @return
     * @throws Exception
     */
    public String getHostSysOS() throws Exception{

        if(System.getProperty("os.name").indexOf("Mac")>=0){
          globalConstants.hostOSName = "MAC";
        }else if (System.getProperty("os.name").indexOf("Windows")>=0){
            globalConstants.hostOSName = "WINDOWS";
        }
        return (globalConstants.hostOSName);
    }

}
