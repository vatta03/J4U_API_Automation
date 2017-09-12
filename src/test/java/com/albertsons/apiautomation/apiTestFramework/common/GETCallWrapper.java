package com.albertsons.apiautomation.apiTestFramework.common;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * Created by Kiran on 8/20/2017.
 * Description: Class Contains all the GET methods from RestAssured GET Call
 */
public class GETCallWrapper extends Generic {

    public GETCallWrapper(){

    }

    /**
     * Description: GET request response as String
     * @param restServiceURL
     * @return
     * @throws Exception
     */
    public String getResponseAsString(String restServiceURL) throws Exception{
        String responseAsString = "Unable to Get Response";
        try {
            responseAsString =
                    when().
                    get(restServiceURL).then().extract().asString();
            return (responseAsString);
        } catch (Exception e){
            e.printStackTrace();
            return (responseAsString);
        }
    }

    /**
     * Description: Get Response As STring using Query and Headers individually
     * @param restServiceURI
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws Exception
     */
    public String getResponseAsString(String restServiceURI,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception{
        String responseAsString = "Unable to Get Response";

        try{
            if (getCallParamsProcess.equalsIgnoreCase("query")){
                responseAsString =
                        given().
                        params(getCallParams).
                        when().
                        get(restServiceURI).then().extract().asString();
            }else if(getCallParamsProcess.equalsIgnoreCase("headers")){
                responseAsString =
                        given().
                        headers(getCallParams).
                        when().
                        get(restServiceURI).then().extract().asString();
            }else if(getCallParamsProcess.equalsIgnoreCase("path")){
                responseAsString =
                        given().
                        pathParams(getCallParams).
                        when().
                        get(restServiceURI).then().extract().asString();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (responseAsString);
    }

    /**
     * Description: Get Response with Query and Header Parameters
     * @param restServiceURI
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */
    public String getResponseAsString(String restServiceURI,Map<String,String> queryParams,Map<String,String> headerParams) throws Exception{
        String responseAsString = "Unable to Get Response";

        try {
            responseAsString =
                    given().
                    params(queryParams).
                    headers(headerParams).
                    when().
                    get(restServiceURI).then().extract().asString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return (responseAsString);
    }

    /**
     * Description: Get the Response As String using Query , Path and Header Params
     * @param restServiceURI
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */
    public String getResponseAsString(String restServiceURI,Map<String,String> queryParams,Map<String,String> headerParams,Map<String,String> pathParams) throws Exception{
        String responseAsString = "Unable to Get Response";

        try {
            responseAsString =
                    given().
                    params(queryParams).
                    headers(headerParams).
                    pathParams(pathParams).
                    when().
                    get(restServiceURI).then().extract().asString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return (responseAsString);
    }

    /**
     * Description: Ges Response Status Code
     * @param restServiceURL
     * @return
     * @throws Exception
     */
    public Integer getStatusCode(String restServiceURL) throws Exception {
        Integer responseStatusCode =
                     when().
                     get(restServiceURL).
                     getStatusCode();
        return (responseStatusCode);
    }

    /**
     * Description: Get Status Code with Query and Header Params
     * @param restServiceURI
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws Exception
     */
    public Integer getStatusCode(String restServiceURI,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception {
        Integer responseStatusCode = 0;

        if (getCallParamsProcess.equalsIgnoreCase("query")){
            responseStatusCode =
                    given().
                    params(getCallParams).
                    when().
                    get(restServiceURI).
                    getStatusCode();

        }else if(getCallParamsProcess.equalsIgnoreCase("headers")){
            responseStatusCode =
                    given().
                    headers(getCallParams).
                    when().
                    get(restServiceURI).
                    getStatusCode();
       }else if(getCallParamsProcess.equalsIgnoreCase("path")){
            responseStatusCode =
                    given().
                    pathParams(getCallParams).
                    when().
                    get(restServiceURI).
                    getStatusCode();
        }
       return (responseStatusCode);
    }


    /**
     * Description: Get Status Code with Query and Headers
     * @param restServiceURI
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */

    public Integer getStatusCode(String restServiceURI,Map<String,String> queryParams,Map<String,String> headerParams) throws Exception {
        Integer responseStatusCode =
                given().
                params(queryParams).
                headers(headerParams).
                when().
                get(restServiceURI).
                getStatusCode();
        return (responseStatusCode);
    }

    /**
     * Description: Get the Status Code using Query , Path and Header Params
     * @param restServiceURI
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */
    public Integer getStatusCode(String restServiceURI,Map<String,String> queryParams,Map<String,String> headerParams,Map<String,String> pathParams) throws Exception {
        Integer responseStatusCode =
                given().
                params(queryParams).
                headers(headerParams).
                pathParams(pathParams).
                when().
                get(restServiceURI).
                getStatusCode();
        return (responseStatusCode);
    }

    /**
     * Description: GET request Response as InputStream
     * @param restServiceURL
     * @return
     * @throws IOException
     */
    public InputStream getResponseAsInputStream(String restServiceURL) throws IOException{
        InputStream responseAsStream = get(restServiceURL).asInputStream();
        return (responseAsStream);
    }

    /**
     * Description: Get Response As Input Stream
     * @param restServiceURI
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws IOException
     */
    public InputStream getResponseAsInputStream(String restServiceURI,Map<String,String> getCallParams,String getCallParamsProcess) throws IOException{
        InputStream responseAsStream = null;
        try{
            if (getCallParamsProcess.equalsIgnoreCase("query")){
                responseAsStream =
                given().
                params(getCallParams).
                when().
                get(restServiceURI).asInputStream();
            }else if(getCallParamsProcess.equalsIgnoreCase("headers")){
                responseAsStream =
                given().
                headers(getCallParams).
                when().
                get(restServiceURI).asInputStream();
            }else if(getCallParamsProcess.equalsIgnoreCase("path")){
                responseAsStream =
                given().
                pathParams(getCallParams).
                when().
                get(restServiceURI).asInputStream();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (responseAsStream);
    }

    /**
     * Description: Get Response As InputStream with Query and Header Parameters together
     * @param restServiceURI
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */
    public InputStream getResponseAsInputStream(String restServiceURI,Map<String,String> queryParams,Map<String,String> headerParams) throws Exception{
        InputStream responseAsStream = null;

        try {
            responseAsStream =
                    given().
                    given().
                    params(queryParams).
                    headers(headerParams).
                    when().
                    get(restServiceURI).asInputStream();
        } catch (Exception e){
            e.printStackTrace();
        }
        return (responseAsStream);
    }

    /**
     * Description: Get Response As InputStream using Query , Path and Header Params
     * @param restServiceURI
     * @param queryParams
     * @param headerParams
     * @param pathParams
     * @return
     * @throws Exception
     */

    public InputStream getResponseAsInputStream(String restServiceURI,Map<String,String> queryParams,Map<String,String> headerParams,Map<String,String> pathParams) throws Exception{
        InputStream responseAsStream = null;

        try {
            responseAsStream =
                    given().
                    given().
                    params(queryParams).
                    headers(headerParams).
                    pathParams(pathParams).
                    when().
                    get(restServiceURI).asInputStream();
        } catch (Exception e){
            e.printStackTrace();
        }
        return (responseAsStream);
    }


    /**
     * Description: Return URL value of JSON URL Element field
     * @param restServiceURL
     * @param urlElementNameToExtract
     * @return
     * @throws Exception
     */
    public String getURLByRestAssuredExtract(String restServiceURL , String urlElementNameToExtract) throws Exception{

        String href =
                when().
                get(restServiceURL).
                then().
                contentType(ContentType.JSON).
                extract().
                path(urlElementNameToExtract);

        return(href);
    }

    /**
     * Description: Get Call with Query Parameters
     * @param restServiceURL
     * @param queryParams
     * @param urlElementNameToExtract
     * @return
     * @throws Exception
     */

    public String getURLByRestAssuredExtract(String restServiceURL , Map<String,String> queryParams ,String urlElementNameToExtract) throws Exception{

        String href =
                given().
                params(queryParams).
                when().
                get(restServiceURL).
                then().
                contentType(ContentType.JSON).
                extract().
                path(urlElementNameToExtract);

        return(href);
    }

    /**
     * Description: GET Response from the Service call
     * @param restServiceURL
     * @return
     * @throws Exception
     */
    public Response getAsResponse(String restServiceURL) throws Exception {

        Response response =
                when().
                get(restServiceURL).
                then().
                extract().
                response();
        return (response);
    }

    /**
     * Description: GET Response from the Service call using query or header parameters
     * @param restServiceURI
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws Exception
     */
    public Response getAsResponse(String restServiceURI,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception {
        Response response = null;
        try{

            if (getCallParamsProcess.equalsIgnoreCase("query")){
                response =
                        given().
                        params(getCallParams).
                        when().
                        get(restServiceURI).
                        then().
                        extract().
                        response();

            }else if(getCallParamsProcess.equalsIgnoreCase("headers")){
                response =
                        given().
                        headers(getCallParams).
                        when().
                        get(restServiceURI).
                        then().
                        extract().
                        response();

            }else if(getCallParamsProcess.equalsIgnoreCase("headers")){
                response =
                        given().
                        pathParams(getCallParams).
                        when().
                        get(restServiceURI).
                        then().
                        extract().
                        response();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (response);
    }


    /**
     * Description: GET Response using query and header params
     * @param restServiceURL
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */

    public Response getAsResponse(String restServiceURL ,Map<String,String> queryParams,Map<String,String> headerParams) throws Exception {
        Response response = null;
        try{
            response =
                    given().
                    params(queryParams).
                    headers(headerParams).
                    when().
                    get(restServiceURL).
                    then().
                    extract().
                    response();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (response);
    }

    /**
     * Description : Get Response using Query , Path and Header Params
     * @param restServiceURL
     * @param queryParams
     * @param headerParams
     * @param pathParams
     * @return
     * @throws Exception
     */

    public Response getAsResponse(String restServiceURL ,Map<String,String> queryParams,Map<String,String> headerParams,Map<String,String> pathParams) throws Exception {
        Response response = null;
        try{
            response =
                    given().
                    params(queryParams).
                    headers(headerParams).
                    pathParams(pathParams).
                    when().
                    get(restServiceURL).
                    then().
                    extract().
                    response();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (response);
    }



    /**
     * Description: Extract JSON Element Int values using Rest Assured JSON Path
     * @param restServiceURL
     * @param jsonElementName
     * @return
     * @throws Exception
     */

    public List<Integer> getJSONElementIntValues(String restServiceURL,String jsonElementName) throws Exception {

        String responseAsString = getResponseAsString(restServiceURL);
        JsonPath jsonPath = new JsonPath(responseAsString);
        List<Integer> extractedList = jsonPath.get(jsonElementName);
        return (extractedList);
    }

    /**
     * Description: Extract JSON Element String values using Rest Assured JSON Path
     * @param restServiceURL
     * @param jsonElementName
     * @return
     * @throws Exception
     */
    public List<String> getJSONElementStringValues(String restServiceURL,String jsonElementName) throws Exception {

        String responseAsString = getResponseAsString(restServiceURL);
        JsonPath jsonPath = new JsonPath(responseAsString);
        List<String> extrctedList = jsonPath.get(jsonElementName);
        return (extrctedList);
    }

    /**
     * Description: Extract JSON Element String values using Rest Assured JSON Path
     * @param restServiceURL
     * @param queryParams
     * @param jsonElementName
     * @return
     * @throws Exception
     */
    public List<String> getJSONElementStringValues(String restServiceURL,Map<String,String> queryParams,String getCallParamsProcess,String jsonElementName) throws Exception {

        String responseAsString = getResponseAsString(restServiceURL,queryParams,getCallParamsProcess);
        JsonPath jsonPath = new JsonPath(responseAsString);
        List<String> extrctedList = jsonPath.get(jsonElementName);
        return (extrctedList);
    }


    /**
     * Description: Get Cookies from the Response
     * @param restServiceURL
     * @return
     * @throws Exception
     */

    public Map<String,String> getCookiesFmResponse(String restServiceURL) throws Exception {

        Response response = getAsResponse(restServiceURL);
        Map<String,String> cookies = response.getCookies();
        return (cookies);

        //Note : get each cookie name and it value
//        for(Map.Entry<String , String> entry : cookies.entrySet()){
//            sout(entry.getKey() + ":" + entry.getValue());
//        }

    }

    /**
     * Description: Get Cookies using Query or header Params
     * @param restServiceURL
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws Exception
     */
    public Map<String,String> getCookiesFmResponse(String restServiceURL,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception {
        Map<String,String> cookies = null;

        try {
            Response response = getAsResponse(restServiceURL,getCallParams,getCallParamsProcess);
            cookies = response.getCookies();
        }catch (Exception e){
            e.printStackTrace();
        }

        return (cookies);

        //Note : get each cookie name and it value
//        for(Map.Entry<String , String> entry : cookies.entrySet()){
//            sout(entry.getKey() + ":" + entry.getValue());
//        }

    }


    /**
     * Get Cookies from Response by Query and Header Params
     * @param restServiceURL
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */
    public Map<String,String> getCookiesFmResponse(String restServiceURL ,Map<String,String> queryParams,Map<String,String> headerParams) throws Exception {
        Map<String,String> cookies = null;

        try {
            Response response = getAsResponse(restServiceURL,queryParams,headerParams);
            cookies = response.getCookies();
        }catch (Exception e){
            e.printStackTrace();
        }

        return (cookies);

        //Note : get each cookie name and it value
//        for(Map.Entry<String , String> entry : cookies.entrySet()){
//            sout(entry.getKey() + ":" + entry.getValue());
//        }

    }


    /**
     * Description: Get Cookies from Response by Query , Path and Header Params
     * @param restServiceURL
     * @param queryParams
     * @param headerParams
     * @param pathParams
     * @return
     * @throws Exception
     */

    public Map<String,String> getCookiesFmResponse(String restServiceURL ,Map<String,String> queryParams,Map<String,String> headerParams,Map<String,String> pathParams) throws Exception {
        Map<String,String> cookies = null;

        try {
            Response response = getAsResponse(restServiceURL,queryParams,headerParams,pathParams);
            cookies = response.getCookies();
        }catch (Exception e){
            e.printStackTrace();
        }

        return (cookies);

        //Note : get each cookie name and it value
//        for(Map.Entry<String , String> entry : cookies.entrySet()){
//            sout(entry.getKey() + ":" + entry.getValue());
//        }

    }


    /**
     * Description: Get Desired Cookie from Response
     * @param restServiceURL
     * @param targetCookieName
     * @return
     * @throws Exception
     */

    public Cookie getTargetCookie(String restServiceURL,String targetCookieName,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception {
        Cookie currentCookie = null;
        try{

            Response response = getAsResponse(restServiceURL,getCallParams,getCallParamsProcess);
            currentCookie = response.getDetailedCookie(targetCookieName);

        }catch (Exception e){
            e.printStackTrace();
        }
        return (currentCookie);

        //Notes:
//        sout("Detailed:"+currentCookie.hasExpiryDate());
//        sout("Detailed:"+currentCookie.getExpiryDate());
//        sout("Detailed:"+currentCookie.hasValue());

    }

    /**
     * Description: Get Target Header from the Response using Query or Header Params
     * @param restServiceURL
     * @param targetHeaderName
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws Exception
     */
    public String getTargetHeader(String restServiceURL,String targetHeaderName,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception{
        String currentHeader = null;
        try{
            Response response = getAsResponse(restServiceURL,getCallParams,getCallParamsProcess);
            currentHeader = response.getHeader(targetHeaderName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (currentHeader);
    }

    /**
     * Description: Get all Headers using Query or Header Params
     * @param restServiceURI
     * @param getCallParams
     * @param getCallParamsProcess
     * @return
     * @throws Exception
     */

    public Headers getAllHeaders (String restServiceURI,Map<String,String> getCallParams,String getCallParamsProcess) throws Exception {
        Response response = null;
        Headers headers = null;

        try{
            response = getAsResponse(restServiceURI,getCallParams,getCallParamsProcess);
            headers = response.getHeaders();

        }catch (Exception e){
            e.printStackTrace();
        }
        return (headers);

        //Notes: get header name and its value
//        for(Header h: headers){
//            sout(h.getName()+":"+h.getValue())
//        }

    }

    /**
     * Description: Get All Headers from the Response using Query params and Header Params
     * @param restServiceURL
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */

    public Headers getAllHeaders (String restServiceURL,Map<String,String> queryParams,Map<String,String> headerParams) throws Exception {
        Response response = getAsResponse(restServiceURL,queryParams,headerParams);
        Headers headers = response.getHeaders();
        return (headers);

        //Notes: get header name and its value
//        for(Header h: headers){
//            sout(h.getName()+":"+h.getValue())
//        }

    }

    /**
     * Description: Get All Headers from the Response using Query , Header and Path Params
     * @param restServiceURL
     * @param queryParams
     * @param headerParams
     * @return
     * @throws Exception
     */
    public Headers getAllHeaders (String restServiceURL,Map<String,String> queryParams,Map<String,String> headerParams,Map<String,String> pathParams) throws Exception {
        Response response = getAsResponse(restServiceURL,queryParams,headerParams,pathParams);
        Headers headers = response.getHeaders();
        return (headers);

        //Notes: get header name and its value
//        for(Header h: headers){
//            sout(h.getName()+":"+h.getValue())
//        }

    }

    /**
     * Description: Get All Headers Keys and Values as One String
     * @param responseHeaders
     * @return
     * @throws Exception
     */
    public String getAllHeadersKeysValues(Headers responseHeaders) throws Exception {
        String headersKeysValues = "Headers Info:-";
        for(Header header: responseHeaders){
            headersKeysValues = headersKeysValues + header.getName()+":"+header.getValue();
        }
        return (headersKeysValues);
    }


    /**
     * Description: Save Response as JSON File for further read
     * @param responseStream
     * @param filename
     * @return
     * @throws Exception
     */

     public String getResponseInputStreamToJSONFile (InputStream responseStream , String filename) throws Exception{
        FileOutputStream outputfile = null;
        String fileName = System.getProperty("user.dir")+"/"+filename+".json";
        try{
            outputfile = new FileOutputStream (fileName);
            byte[] buffer = new byte[2048];
            for (int length = 0; (length = responseStream.read(buffer)) > 0;) {
                outputfile.write(buffer, 0, length);
            }
        }finally {
            if (outputfile != null) try { outputfile.close(); } catch (IOException logOrIgnore) {};
            if (responseStream != null) try { responseStream.close(); } catch (IOException logOrIgnore) {};
        }
        return (fileName);
    }




}
