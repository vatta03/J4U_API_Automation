package com.albertsons.apiautomation.j4uAPI.automation.apiTests.miscellaneousSampleAPIs.sample;

import com.albertsons.apiautomation.apiTestFramework.common.GETCallWrapper;
import com.albertsons.apiautomation.apiTestFramework.common.Generic;
import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.common.AppGeneric;
import com.albertsons.apiautomation.j4uAPI.automation.common.DataExtractor;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Created by Kiran on 9/3/2017.
 */
public class VerifyGetWeatherReportByIdTest extends ConfigTestBase {

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
    public Map<String ,String> queryParams = new HashMap<String,String>();

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
        currentTSDataRowsJSONArray = dataExtractor.getJSONParseTestData(GlobalConstants.testEnvironment,globalConstants.currentTestAPIGroupName,globalConstants.currentTestAPI ,testScriptName);
    }

    @Test
    public void VerifyGetWeatherReportByIdTest () throws Exception {

        Iterator testScriptIterator = currentTSDataRowsJSONArray.iterator();
        while (testScriptIterator.hasNext()){
            Object keyObj = testScriptIterator.next();
            globalConstants.currentTSDataRowJSONObj = (JSONObject) keyObj;
            if(globalConstants.currentTSDataRowJSONObj.get("execute").toString().equalsIgnoreCase("yes")){
                //Initializing Rest API's URL
                queryParams.put("id",globalConstants.currentTSDataRowJSONObj.get("id").toString());
                queryParams.put("appid",globalConstants.currentTSDataRowJSONObj.get("appid").toString());
                restAssuredObj.baseURI = globalConstants.currentTSDataRowJSONObj.get("Uri").toString();

                //Step 1:
                appGeneric.assignDetails("Test Script Name: " + testScriptName, "This Test is Currently Runs on API - " + globalConstants.currentTestAPI
                        + "," + "Current Test Environment:-" + GlobalConstants.testEnvironment + "," + "Current Test API BasePath:=" + restAssuredObj.baseURI.toString(), "Unable to Run the Test");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);

                //Step 2:
                //Call Weather Report Service
                statusCode = getCallWrapper.getStatusCode(restAssuredObj.baseURI,queryParams, GlobalConstants.GetCallArgs.QUERYPARAM.toString());
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI,queryParams, GlobalConstants.GetCallArgs.QUERYPARAM.toString());

                appGeneric.assignDetails("Verify Service Call Status Code", "Service Call is Successful - StatusCode:-" + statusCode, "Service Call Error " + responseDetails);
                Assert.assertTrue(generic.verifyStatusCode(statusCode), "Service Call Error " + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 3: Verify Response Type as JSON format
                appGeneric.assignDetails("Verify Response Content Type", "Response Content Type is JSON", "Response Content Type is not JSON" );
                Assert.assertTrue(getCallWrapper.verifyResponseContentType(restAssuredObj.baseURI),"Response Content Type is not JSON" );
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 4:
                //Get Weather Response By ID
                appGeneric.assignDetails("GET Weather Reponse By ID", "Weather API GET Response By ID-" + responseDetails, "Unable to Extract Date from GET Response");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);
//
//                //Step 5: Verify given id exist in the Response
                appGeneric.assignDetails("Verify City ID -" +  globalConstants.currentTSDataRowJSONObj.get("id").toString() + "from the Response", "Correct City ID is displayed: " + responseDetails, "In Correct City ID is displayed in the GET Response" + responseDetails);
                Assert.assertTrue(responseDetails.contains(globalConstants.currentTSDataRowJSONObj.get("id").toString()),"In Correct City ID is displayed in the GET Response" + responseDetails);
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
