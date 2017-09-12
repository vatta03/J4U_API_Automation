package com.albertsons.apiautomation.j4uAPI.automation.apiTests.miscellaneousSampleAPIs.sample;

import com.albertsons.apiautomation.apiTestFramework.common.GETCallWrapper;
import com.albertsons.apiautomation.apiTestFramework.common.Generic;
import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.common.AppGeneric;
import com.albertsons.apiautomation.j4uAPI.automation.common.DataExtractor;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * Created by kedupuganti on 8/21/2017.
 */
public class VerifyURLElementValueTest extends ConfigTestBase {

    public GETCallWrapper getCallWrapper = null;
    public DataExtractor dataExtractor = null;
    public GlobalConstants globalConstants = null;
    public RestAssured restAssuredObj = new RestAssured();
    public String testScriptName = null;
    public Integer statusCode = null;
    public String responseDetails = null;
    private Generic generic = null;
    private AppGeneric appGeneric = null;
    public JSONArray currentTSDataRowsJSONArray = null;
    public String thumbNailURLValue = null;

    @Parameters({"apiGroupName", "apiName"})
    @BeforeMethod
    public void initializeDataExtractor(String apiGroupName,String currentAPIName) throws Exception {
        getCallWrapper = new GETCallWrapper();
        dataExtractor = new DataExtractor();
        globalConstants = new GlobalConstants();
        globalConstants.currentTestAPI = currentAPIName;
        globalConstants.currentTestAPIGroupName = apiGroupName;
        generic = new Generic();
        appGeneric = new AppGeneric();
        testScriptName = this.getClass().getSimpleName();
        currentTSDataRowsJSONArray = dataExtractor.getJSONParseTestData(GlobalConstants.testEnvironment, globalConstants.currentTestAPIGroupName, globalConstants.currentTestAPI, testScriptName);
    }

    @Test
    public void VerifyURLElementValueTest () throws Exception {

        Iterator testScriptIterator = currentTSDataRowsJSONArray.iterator();
        while (testScriptIterator.hasNext()){
            Object keyObj = testScriptIterator.next();
            globalConstants.currentTSDataRowJSONObj = (JSONObject) keyObj;
            if(globalConstants.currentTSDataRowJSONObj.get("execute").toString().equalsIgnoreCase("yes")){
                //Initializing Rest API's URL
                restAssuredObj.baseURI = globalConstants.currentTSDataRowJSONObj.get("Url").toString();
                //Step 1:
                appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test is Currently Runs on API - " + globalConstants.currentTestAPI
                        +","+"Curretn Test Environemnt:-" + GlobalConstants.testEnvironment + "," + "Current Test API BasePath:=" + restAssuredObj.baseURI, "Unable to Run the Test");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);
                //Step 2:
                //Call JSON Placeholder service
                statusCode = getCallWrapper.getStatusCode(restAssuredObj.baseURI);
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI);
                appGeneric.assignDetails("Verify Service Call Status Code", "Service Call is Successful - StatusCode:-" + statusCode, "Service Call Error " + responseDetails);
                Assert.assertTrue(generic.verifyStatusCode(statusCode), "Service Call Error " + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 3: Verify Place holder Service Response as JSON format
                appGeneric.assignDetails("Verify Response Content Type", "Response Content Type is JSON", "Response Content Type is not JSON" );
                Assert.assertTrue(getCallWrapper.verifyResponseContentType(restAssuredObj.baseURI),"Response Content Type is not JSON" );
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step4:
                appGeneric.assignDetails("GET thumbNailURL Element Value", "thumbNailURL Element Value is Successfully Extracted - " + thumbNailURLValue, "Unable to Extract thumbNailURL Element Value");
                thumbNailURLValue = getCallWrapper.getURLByRestAssuredExtract(restAssuredObj.baseURI,"thumbnailUrl");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 5:
                //Verify URL Value
                Assert.assertTrue(thumbNailURLValue.contentEquals("http://placehold.it/150/92c952"), "In Correct thumbnailUrl Element Value in the GET Response" + thumbNailURLValue);
                appGeneric.assignDetails("Verify thumbNailURL Element Value", "Correct thumbNailURL Element Value is present in the GET Response :" + thumbNailURLValue, "In Correct thumbnailUrl Element Value in the GET Response" + thumbNailURLValue);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

            }else {
                if (currentTSDataRowsJSONArray.size()>1){
                    appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test Script row is currently not set for Execution , hence Skipping Execution.....", "Unable to Run the Test");
                    appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);
                }else {
                    appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test Script is currently not set for Execution , hence Skipping Execution.....", "Unable to Run the Test");
                    appGeneric.generateExtentReport(GlobalConstants.ReportStatus.SKIP.toString(), testScriptName);
                }

            }

        }

    }


}
