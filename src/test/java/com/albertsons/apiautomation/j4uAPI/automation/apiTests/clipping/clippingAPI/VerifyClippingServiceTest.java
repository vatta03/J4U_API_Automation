package com.albertsons.apiautomation.j4uAPI.automation.apiTests.clipping.clippingAPI;

import com.albertsons.apiautomation.apiTestFramework.common.GETCallWrapper;
import com.albertsons.apiautomation.apiTestFramework.common.Generic;
import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.common.AppGeneric;
import com.albertsons.apiautomation.j4uAPI.automation.common.DataExtractor;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by kedupuganti on 8/21/2017.
 * Service: Clipping
 * Method: POST
 */
public class VerifyClippingServiceTest extends ConfigTestBase {

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
    public Headers reponseHeaders = null;
    public String allHeadersKeysValues = null;
    public Map<String ,String> queryParams = new HashMap<String,String>();
    public Map<String, String> headersParams = new HashMap<String, String>();
    public JSONObject tokenJSONDataObj = null;

    @BeforeMethod
    public void initializeDataExtractor(String apiGroupName,String currentAPIName) throws Exception {
        getCallWrapper = new GETCallWrapper();
        dataExtractor = new DataExtractor();
        globalConstants = new GlobalConstants();
        generic = new Generic();
        appGeneric = new AppGeneric();
        testScriptName = this.getClass().getSimpleName();
        currentTSDataRowsJSONArray = dataExtractor.getJSONParseTestData(GlobalConstants.testEnvironment,gConstants.currentTestAPIGroupName,gConstants.currentTestAPI,testScriptName);
    }

    @Test
    public void VerifyClippingServiceTest () throws Exception {

        Iterator testScriptIterator = currentTSDataRowsJSONArray.iterator();
        while (testScriptIterator.hasNext()){
            Object keyObj = testScriptIterator.next();
            globalConstants.currentTSDataRowJSONObj = (JSONObject) keyObj;
            if(globalConstants.currentTSDataRowJSONObj.get("execute").toString().equalsIgnoreCase("yes")){

                //Initializing Rest API's URL
                queryParams.put("storeId",globalConstants.currentTSDataRowJSONObj.get("storeId").toString());
                if (globalConstants.currentTSDataRowJSONObj.get("authenticationRequired").toString().equalsIgnoreCase("yes")){
                    tokenJSONDataObj = dataExtractor.getJSONParseTokenTestData(GlobalConstants.testEnvironment,globalConstants.tokenServiceGroupName, globalConstants.tokenServiceAPIName);
                    headersParams.put("X-swyConsumerDirectoryPro", getCallWrapper.getTokenId(tokenJSONDataObj));
                }
                restAssuredObj.baseURI = globalConstants.currentTSDataRowJSONObj.get("uri").toString();

                //Step 1:
                appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test is Currently Runs on API - " + gConstants.currentTestAPI
                        +","+"Current Test Environment:-" + GlobalConstants.testEnvironment + "," + "Current Test API BasePath:=" + restAssuredObj.baseURI, "Unable to Run the Test");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);

                //Step 2:
                //Call Service
                statusCode = getCallWrapper.getStatusCode(restAssuredObj.baseURI,queryParams,headersParams);
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI,queryParams,headersParams);
                appGeneric.assignDetails("Verify Service Call Status Code", "Service Call is Successful - StatusCode:-" + statusCode, "Service Call Error " + responseDetails);
                Assert.assertTrue(generic.verifyStatusCode(statusCode),"Service Call Error " + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 3: Verify Response Type as JSON
                appGeneric.assignDetails("Verify Response Content Type", "Response Content Type is JSON", "Response Content Type is not JSON" );
                Assert.assertTrue(getCallWrapper.verifyResponseContentType(restAssuredObj.baseURI,queryParams,headersParams),"Response Content Type is not JSON" );
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 4:
                //Get Clipping Service Details
                appGeneric.assignDetails("Clipping Service Details", "Clipping Service Details are Extracted from GET Response-" + responseDetails, "Unable to Extract My List Count Details from the GET Response");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 5: Verify Clipping API service
                appGeneric.assignDetails("Verify Clipping API details in the Service Response", "Correct details should be displayed in the GET Response: " + responseDetails, "In Correct details is displayed in the GET Response" + responseDetails);
                Assert.assertTrue(responseDetails.contains("1502"),"In Correct Store Id is displayed in the GET Response" + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 5: Get Headers from the Response
                reponseHeaders = getCallWrapper.getAllHeaders(restAssuredObj.baseURI,queryParams,headersParams);
                allHeadersKeysValues = getCallWrapper.getAllHeadersKeysValues(reponseHeaders);
                appGeneric.assignDetails("Get All Headers and Values from the Response", "Headers Keys and Values are extracted Successfully: " + allHeadersKeysValues, "Unable to Headers Keys and Values from the GET Response" + allHeadersKeysValues);
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
