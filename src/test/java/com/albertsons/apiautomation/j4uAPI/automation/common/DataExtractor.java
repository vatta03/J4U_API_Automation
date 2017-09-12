package com.albertsons.apiautomation.j4uAPI.automation.common;

import com.albertsons.apiautomation.j4uAPI.automation.constants.GlobalConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Kiran on 8/26/2017.
 */
public class DataExtractor {

    private AppGeneric appGeneric = new AppGeneric();
    public JSONObject currentTestDataJSONObj = null;
    public JSONObject currentEnvTokenDataJSONObj = null;
    public JSONArray currentTSTestDataArray = null;


    /**
     * Description: Get Test Data from the respective JSON file for test script under execution
     * @param testEnvironment
     * @param apiGroupName
     * @param apiName
     * @param testScriptName
     * @return
     * @throws Exception
     */

    public JSONArray getJSONParseTestData(String testEnvironment ,String apiGroupName, String apiName , String testScriptName) throws Exception {

        try {


            String filePath = null;
            //Get current OS Name and path for Test Data
            if (appGeneric.getHostSysOS().equalsIgnoreCase("mac")){
                filePath = GlobalConstants.testDataFilePathMac + apiGroupName + "//" + apiName.toUpperCase()+".json";
            }else if(appGeneric.getHostSysOS().equalsIgnoreCase("windows")){
                filePath = GlobalConstants.testDataFilePathWindows + apiGroupName + "\\" + apiName.toUpperCase()+".json";
            }
            // read the json file
            System.out.println("jSon File Path::"+filePath);
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            // handle a structure into the json object
            currentTestDataJSONObj = (JSONObject) jsonObject.get(testEnvironment);
            System.out.println("print test script name" + testScriptName);
            currentTSTestDataArray = (JSONArray) currentTestDataJSONObj.get(testScriptName);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return (currentTSTestDataArray);
    }

    /**
     * Description: Get the Token Information based on Environment
     * @param testEnvironment
     * @param apiGroupName
     * @param apiName
     * @return
     * @throws Exception
     */
    public JSONObject getJSONParseTokenTestData(String testEnvironment,String apiGroupName,String apiName) throws Exception {

        try {
            String filePath = null;
            //Get current OS Name and path for Test Data
            if (appGeneric.getHostSysOS().equalsIgnoreCase("mac")){
                filePath = GlobalConstants.testDataFilePathMac + apiGroupName + "//" + apiName.toUpperCase()+".json";
            }else if(appGeneric.getHostSysOS().equalsIgnoreCase("windows")){
                filePath = GlobalConstants.testDataFilePathWindows + apiGroupName + "\\" + apiName.toUpperCase()+".json";
            }
            // read the json file
            System.out.println("jSon File Path::"+filePath);
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            // handle a structure into the json object
            currentEnvTokenDataJSONObj = (JSONObject) jsonObject.get(testEnvironment);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return (currentEnvTokenDataJSONObj);
    }

}
