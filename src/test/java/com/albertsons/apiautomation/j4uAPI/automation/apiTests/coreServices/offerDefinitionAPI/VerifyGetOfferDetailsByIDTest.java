package com.albertsons.apiautomation.j4uAPI.automation.apiTests.coreServices.offerDefinitionAPI;

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
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kedupuganti on 8/21/2017.
 * Service: Offer Definition by ID
 * Method: GET
 */
public class VerifyGetOfferDetailsByIDTest extends ConfigTestBase {

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
    public void VerifyGetOfferDetailsByIDTest () throws Exception {

        Iterator testScriptIterator = currentTSDataRowsJSONArray.iterator();
        while (testScriptIterator.hasNext()){
            Object keyObj = testScriptIterator.next();
            globalConstants.currentTSDataRowJSONObj = (JSONObject) keyObj;
            if(globalConstants.currentTSDataRowJSONObj.get("execute").toString().equalsIgnoreCase("yes")){

                //Initializing Rest API's URL
                queryParams.put("offerId",globalConstants.currentTSDataRowJSONObj.get("offerID").toString());
                queryParams.put("offerTs",globalConstants.currentTSDataRowJSONObj.get("offerTs").toString());
                restAssuredObj.baseURI = globalConstants.currentTSDataRowJSONObj.get("uri").toString();


                //Step 1:
                appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test is Currently Runs on API - " + gConstants.currentTestAPI
                        +","+"Current Test Environment:-" + GlobalConstants.testEnvironment + "," + "Current Test API BasePath:=" + restAssuredObj.baseURI.toString(), "Unable to Run the Test");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);

                //Step 2: Service hit - Call Offer Details ID Service
                statusCode = getCallWrapper.getStatusCode(restAssuredObj.baseURI,queryParams,GlobalConstants.GetCallArgs.QUERYPARAM.toString());
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI,queryParams,GlobalConstants.GetCallArgs.QUERYPARAM.toString());

                appGeneric.assignDetails("Verify Service Call Status Code", "Service Call is Successful - StatusCode:-" + statusCode, "Service Call Error " + responseDetails);
                Assert.assertTrue(generic.verifyStatusCode(statusCode),"Service Call Error " + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 3: Verify Offer Content Type as JSON
                appGeneric.assignDetails("Verify Response Content Type", "Response Content Type is JSON", "Response Content Type is not JSON" );
                Assert.assertTrue(getCallWrapper.verifyResponseContentType(restAssuredObj.baseURI,queryParams,GlobalConstants.GetCallArgs.QUERYPARAM.toString()),"Response Content Type is not JSON" );
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 4: Get Offer Details By ID
                appGeneric.assignDetails("GET Offer Details Using Offer ID", "Offer Details are Extracted from GET Response using Offer ID -" + responseDetails, "Unable to Extract Offer Details from the GET Response");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 5: Verify Offer ID from the Response
                appGeneric.assignDetails("Verify OfferID -" +   globalConstants.currentTSDataRowJSONObj.get("offerID").toString() +" from the Service Response", "Extracted Offer Details Correct:= OFFER DETAILS " + responseDetails, "InCorrect Offer Details has been extracted" + responseDetails);
                Assert.assertTrue(responseDetails.contains(globalConstants.currentTSDataRowJSONObj.get("offerID").toString()),"InCorrect Offer Details has been extracted" + responseDetails);
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
