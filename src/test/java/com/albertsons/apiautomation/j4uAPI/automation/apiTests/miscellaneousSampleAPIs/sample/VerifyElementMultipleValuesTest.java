package com.albertsons.apiautomation.j4uAPI.automation.apiTests.miscellaneousSampleAPIs.sample;

import com.albertsons.apiautomation.apiTestFramework.common.Generic;
import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.common.AppGeneric;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Kiran on 8/20/2017.
 */
public class VerifyElementMultipleValuesTest extends ConfigTestBase {

    public GlobalConstants globalConstants = null;
    public RestAssured restAssuredObj = new RestAssured();
    private Generic generic = null;
    private AppGeneric appGeneric = null;

    @Parameters({"testData", "api"})
    @BeforeMethod
    public void initializeDataExtractor(String testDataFileName,String currentAPI) throws Exception {
        globalConstants = new GlobalConstants();
        globalConstants.currentTestAPI = currentAPI;
        generic = new Generic();
        appGeneric = new AppGeneric();
        String scriptName = this.getClass().getSimpleName();
        //Initializing Rest API's URL
        restAssuredObj.baseURI = "http://services.groupkt.com/country/get/all";
    }

    @Test
    public void VerifyElementMultipleValuesTest () throws Exception {

//        appGeneric.assignDetails("Current Base Test URI:" + restAssuredObj.baseURI, "This Test is Currently Runs on API - " + globalConstants.currentTestAPI, "Unable to Run the Test");
//        appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), "VerifyElementSingleValueTest");
//        appGeneric.assignDetails("Verify RestResponse.result.name Element Values in the GET Response", "name Element values are Correct in the GET Response", "In Correct name Element values are in the GET Response");
//        Assert.assertTrue(generic.verifyMultipleValuesInJSONElement(restAssuredObj.baseURI, "RestResponse.result.name", "Afghanistan", "Argentina", "Australia"));
//        appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), "VerifyElementSingleValueTest");
    }



}
