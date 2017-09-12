package com.albertsons.apiautomation.j4uAPI.automation.reportGeneration;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * Created by kedupuganti on 8/18/2017.
 */
public class ExtentManager {

    static ExtentReports extent;
    final static String filePath = "APITestExecutionReport.html";

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }

        return extent;
    }


}
