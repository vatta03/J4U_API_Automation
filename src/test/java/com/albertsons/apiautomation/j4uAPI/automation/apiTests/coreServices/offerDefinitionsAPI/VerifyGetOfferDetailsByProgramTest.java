package com.albertsons.apiautomation.j4uAPI.automation.apiTests.coreServices.offerDefinitionsAPI;

import com.albertsons.apiautomation.apiTestFramework.common.GETCallWrapper;
import com.albertsons.apiautomation.apiTestFramework.common.Generic;
import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.common.AppGeneric;
import com.albertsons.apiautomation.j4uAPI.automation.common.DataExtractor;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import io.restassured.RestAssured;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kedupuganti on 8/21/2017.
 * Service: Internal-Offer Definitions (By Program)
 * Method: GET
 */
public class VerifyGetOfferDetailsByProgramTest extends ConfigTestBase {

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
    public Map<String, String> queryParams = new HashMap<String, String>();
    public Map<String, String> headersParams = new HashMap<String, String>();
    public JSONObject tokenJSONDataObj = null;

    @Parameters({"apiGroupName", "apiName"})
    @BeforeMethod
    public void initializeDataExtractor() throws Exception {
        getCallWrapper = new GETCallWrapper();
        dataExtractor = new DataExtractor();
        globalConstants = new GlobalConstants();
        generic = new Generic();
        appGeneric = new AppGeneric();
        testScriptName = this.getClass().getSimpleName();
        currentTSDataRowsJSONArray = dataExtractor.getJSONParseTestData(GlobalConstants.testEnvironment,gConstants.currentTestAPIGroupName,gConstants.currentTestAPI,testScriptName);
    }

    @Test
    public void VerifyGetOfferDetailsByProgramTest () throws Exception {

        Iterator testScriptIterator = currentTSDataRowsJSONArray.iterator();
        while (testScriptIterator.hasNext()){
            Object keyObj = testScriptIterator.next();
            globalConstants.currentTSDataRowJSONObj = (JSONObject) keyObj;
            if(globalConstants.currentTSDataRowJSONObj.get("execute").toString().equalsIgnoreCase("yes")){
                //Initializing Rest API's URL
                if (globalConstants.currentTSDataRowJSONObj.get("authenticationRequired").toString().equalsIgnoreCase("yes")){
                    tokenJSONDataObj = dataExtractor.getJSONParseTokenTestData(GlobalConstants.testEnvironment,globalConstants.tokenServiceGroupName, globalConstants.tokenServiceAPIName);
                    headersParams.put("X-swyConsumerDirectoryPro", getCallWrapper.getTokenId(tokenJSONDataObj));
                }
                restAssuredObj.baseURI = globalConstants.currentTSDataRowJSONObj.get("uri").toString();

                //Step 1:
                appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test is Currently Runs on API - " + gConstants.currentTestAPI
                        +","+"Current Test Environment:-" + GlobalConstants.testEnvironment + "," + "Current Test API BasePath:=" + restAssuredObj.baseURI, "Unable to Run the Test");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);

                //Step 2: Call Offer Details by program Service
                statusCode = getCallWrapper.getStatusCode(restAssuredObj.baseURI,headersParams,GlobalConstants.GetCallArgs.HEADERPARAM.toString());
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI,headersParams,GlobalConstants.GetCallArgs.HEADERPARAM.toString());

                appGeneric.assignDetails("Verify Service Call Status Code", "Service Call is Successful - StatusCode:-" + statusCode, "Service Call Error " + responseDetails);
                Assert.assertTrue(generic.verifyStatusCode(statusCode),"Service Call Error " + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 3: Verify Offer Details Type as JSON format
                appGeneric.assignDetails("Verify Response Content Type", "Response Content Type is JSON", "Response Content Type is not JSON" );
                Assert.assertTrue(getCallWrapper.verifyResponseContentType(restAssuredObj.baseURI,headersParams,GlobalConstants.GetCallArgs.HEADERPARAM.toString()),"Response Content Type is not JSON" );
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 4: Get Offer Details By Program
                appGeneric.assignDetails("GET Offer Details Using Offer Program", "Offer Details are Extracted from GET Response using Offer Program -" + responseDetails, "Unable to Extract Offer Details from the GET Response");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 5: Verify Offer Program from the Response
                appGeneric.assignDetails("Verify OfferProgram from the Service Response", "Extracted Offer Details Correct:= OFFER DETAILS " + responseDetails, "InCorrect Offer Details has been extracted" + responseDetails);
                Assert.assertTrue(responseDetails.contains("282865"),"InCorrect Offer Details has been extracted" + responseDetails);
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
