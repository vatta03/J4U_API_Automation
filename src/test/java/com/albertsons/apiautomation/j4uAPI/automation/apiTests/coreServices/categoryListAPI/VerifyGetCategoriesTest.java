package com.albertsons.apiautomation.j4uAPI.automation.apiTests.coreServices.categoryListAPI;

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
 * Created by kedupuganti on 8/22/2017.
 * Service: Categories
 * Method: GET
 */
public class VerifyGetCategoriesTest extends ConfigTestBase {

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
    public void VerifyGetCategoriesTest () throws Exception {

        Iterator testScriptIterator = currentTSDataRowsJSONArray.iterator();
        while (testScriptIterator.hasNext()){
            Object keyObj = testScriptIterator.next();
            globalConstants.currentTSDataRowJSONObj = (JSONObject) keyObj;
            if(globalConstants.currentTSDataRowJSONObj.get("execute").toString().equalsIgnoreCase("yes")){

                //Initializing Rest API's URL
                queryParams.put("superoffer",globalConstants.currentTSDataRowJSONObj.get("superoffer").toString());
                restAssuredObj.baseURI = globalConstants.currentTSDataRowJSONObj.get("uri").toString();

                //Step 1:

                appGeneric.assignDetails("Test Script Name: " + testScriptName , "This Test is Currently Runs on API - " + gConstants.currentTestAPI
                        +","+"Current Test Environment:-" + GlobalConstants.testEnvironment + "," + "Current Test API BasePath:=" + restAssuredObj.baseURI.toString(), "Unable to Run the Test");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), testScriptName);

                //Step 2:
                //Call Category List Service
                statusCode = getCallWrapper.getStatusCode(restAssuredObj.baseURI,queryParams,GlobalConstants.GetCallArgs.QUERYPARAM.toString());
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI,queryParams,GlobalConstants.GetCallArgs.QUERYPARAM.toString());

                appGeneric.assignDetails("Verify Service Call Status Code", "Service Call is Successful - StatusCode:-" + statusCode, "Service Call Error " + responseDetails);
                Assert.assertTrue(generic.verifyStatusCode(statusCode),"Service Call Error " + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 3: Verify Category Type as JSON format
                appGeneric.assignDetails("Verify Response Content Type", "Response Content Type is JSON", "Response Content Type is not JSON" );
                Assert.assertTrue(getCallWrapper.verifyResponseContentType(restAssuredObj.baseURI,queryParams,GlobalConstants.GetCallArgs.QUERYPARAM.toString()),"Response Content Type is not JSON" );
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 4:
                //Get Category List
                appGeneric.assignDetails("GET Category List", "Category List are Extracted from GET Response-" + responseDetails, "Unable to Extract Category List from the GET Response");
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);

                //Step 5: Verify Category Name "Baby Care" from the Response
                appGeneric.assignDetails("Verify Category Name Baby Care from the Response", "Correct Category Name is displayed: " + responseDetails, "In Correct Category Name is displayed in the GET Response" + responseDetails);
                Assert.assertTrue(responseDetails.contains("Baby Care"),"In Correct Category is displayed in the GET Response" + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);


                /* Need to work on this
                //Step 6: Verifying when query parameters ?supperoffer=n
                currentURL = globalConstants.currentTSDataRowJSONObj.get("url").toString() + globalConstants.currentTSDataRowJSONObj.get("resourceInfo").toString()+globalConstants.currentTSDataRowJSONObj.get("catSuperOffersNo").toString();
                restAssuredObj.baseURI = currentURL;
                responseDetails = getCallWrapper.getResponseAsString(restAssuredObj.baseURI);
                appGeneric.assignDetails("Verify Category Code 23 should not exist when superoffer=n in the responsne" + "Url:-" + currentURL, "Category Code 23 is filtered out and not exist in the response " + responseDetails, "Category Code 23 is not filtered and exist in the Response" + responseDetails);
                Assert.assertFalse(responseDetails.contains("Special Offers"),"Category Code 23 is displayed in the GET Response" + responseDetails);
                appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), testScriptName);
    */
                    //Step 7: JSON Structure Need to be verified , working on this ---

    //        restAssuredObj.baseURI = "https://nimbus-qa1.safeway.com/J4UProgram1/services/offer/categories?superoffer=y";
    //        InputStream responseInputStream = getCallWrapper.getResponseAsInputStream(restAssuredObj.baseURI);
    //        String jsonfileName = getCallWrapper.getResponseInputStreamToJSONFile(responseInputStream,"tempJsonFiles/"+testScriptName);


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
