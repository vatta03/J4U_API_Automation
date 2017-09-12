package com.albertsons.apiautomation.apiTestFramework.common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Created by Kiran on 8/20/2017.
 * Description: Class Contains all the POST methods from RestAssured POST Call
 */
public class POSTCallWrapper extends Generic {

    public POSTCallWrapper () {

    }

    /**
     * Description:
     * @param restURI
     * @param dataParams
     * @return
     * @throws Exception
     */
    public Response performPostCall (String restURI , Map<String,String> dataParams) throws Exception{
        Response response = null;
        try {
            response =
                    given().
                    params(dataParams).
//                            params(String ).
                    when().
                    contentType(ContentType.JSON).
                    body(dataParams).
                    put(restURI);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return(response);
    }

}
