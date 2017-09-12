package com.albertsons.apiautomation.j4uAPI.automation.constants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by kedupuganti on 8/18/2017.
 */
public class GlobalConstants {

    public static int stepNo = 0;
    public String stepDescription = null;
    public static String stepExpected = null;
    public String stepPassActual = null;
    public String stepFailActual = null;
    public static  String testEnvironment = null;
    public String currentTestAPI = null;
    public String currentTestAPIGroupName = null;
    public static final String testDataFilePathWindows = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\";
    public static final String testDataFilePathMac = System.getProperty("user.dir")+"//src//test//resources//TestData//";
    public String hostOSName = null;
    public JSONObject currentTSDataRowJSONObj= null;
    public String tokenServiceGroupName = "tokenService";
    public String tokenServiceAPIName = "TOKENSERVICE";


    public enum ReportStatus {
        PASS {
            @Override
            public String toString() {return "Pass";}
        },
        FAIL {
            @Override
            public String toString() {return "Fail";}
        },
        SKIP {
            @Override
            public String toString() {return "Skip";}
        },
        INFO {
            @Override
            public String toString() {return "Info";}
        }

    }

    public enum GetCallArgs {

        QUERYPARAM {
            @Override
            public String toString() {return "Query";}
        },
        HEADERPARAM {
            @Override
            public String toString() {return "Headers";}
        },
        PATHPARAM {
            @Override
            public String toString() {return "Path";}
        }

    }


    public enum VerifyTextOptions {
        EXACTMATCH,
        EXACTMATCHIGNORECASE,
        PARTIAL
    }

    public enum VerifyValueOptions {
        EQUALTO,
        GREATERTHAN,
        LESSTHAN
    }

    public enum RegisterNewUserSetting {
        ENV {
            @Override
            public String toString() {
                switch (testEnvironment.toUpperCase()) {
                    case "QA1":
                        return "https://ngcp-qa1.safeway.com/iass/service/register";
                    case "QA2":
                        return "https://ngcp-qa2.safeway.com/iass/service/register";
                    case "PROD":
                        return "https://www.safeway.com/iass/service/register";
                    default:
                        return "https://www.safeway.com/iass/service/register";
                }
            }
        },
        WEBENV{
            @Override
            public String toString(){
                switch (testEnvironment.toUpperCase()) {
                    case "QA1":
                        return "https://ngcp-qa1.safeway.com/";
                    case "QA2":
                        return "https://ngcp-qa2.safeway.com/";
                    case "PROD":
                        return "https://www.safeway.com/";
                    default:
                        return "https://www.safeway.com/";
                }
            }
        }
    }

}
