package com.albertsons.apiautomation.j4uAPI.automation.apiTests.miscellaneousSampleAPIs.registration;

import com.albertsons.apiautomation.apiTestFramework.common.Generic;
import com.albertsons.apiautomation.j4uAPI.automation.base.ConfigTestBase;
import com.albertsons.apiautomation.j4uAPI.automation.common.AppGeneric;
import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by kedupuganti on 8/18/2017.
 */
public class VerifyRegisterUserNegativeTest extends ConfigTestBase {

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
        restAssuredObj.baseURI = GlobalConstants.RegisterNewUserSetting.ENV.toString();
    }

    @Test
    public void VerifyRegisterUserNegativeTest () throws Exception{

//        appGeneric.assignDetails("Current Base Test URI:" + restAssuredObj.baseURI, "This Test is Currently Runs on API -" + globalConstants.currentTestAPI, "");
//        appGeneric.generateExtentReport(GlobalConstants.ReportStatus.INFO.toString(), "VerifyRegisterUserTest");
//
//        //Initializing payload
//        String apiBody = "{\"zipCode\":\"95476\",\"password\":\"testpwd1\", \"confUserId\":\"testauto_20170728135271@example.com\", \"securityQuestion\":\"Q5\", \"securityAnswer\":\"test\", \"emailOffers\":\"Y\", \"confPwd\":\"testpwd1\", \"banner\":\"safeway\", \"rememberMe\":\"Y\", \"source\":\"RSSWEB\", \"userId\":\"testauto_20170728135271@example.com\", \"clubCardNumber\":\"40000000384\"}";
//        //Step 1:
//        //Script the Response
//        Response responseObj = given()
//                .contentType("application/json; charset=UTF-8")
//                .body(apiBody)
//                .when()
//                .post(restAssuredObj.baseURI);
//        //generic.assignDetails("Test Register User", "User is created successfully" + "testauto_20170728135271@example.com", "Unable to Create the User -" + "Status Code: " + responseObj.getStatusCode() + "Payload Message: " + responseObj.getBody().asString());
//      //  Assert.assertTrue(generic.verifyValue(201,responseObj.getStatusCode(), GlobalConstants.VerifyValueOptions.EQUALTO),GlobalConstants.stepFailActual);
//        //ExtentTestManager.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), "VerifyRegisterUserTest");
//        //Step 2:
//        appGeneric.assignDetails("Get Status Code", "Status Code is retrieved successfully -" + responseObj.getStatusCode(), "Unable to get the Status code");
//        appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), "VerifyRegisterUserTest");
//        //Step 3:
//        appGeneric.assignDetails("Get body of the Response", "Response body is retrieved successfully -" + responseObj.getBody().asString(), "Unable to get the response body");
//        appGeneric.generateExtentReport(GlobalConstants.ReportStatus.PASS.toString(), "VerifyRegisterUserTest");

    }

}
