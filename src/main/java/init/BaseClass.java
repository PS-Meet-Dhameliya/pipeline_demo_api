  package init;

import Utility.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.groovy.parser.antlr4.GroovyParser.SuperPrmrAltContext;
import org.hamcrest.Matchers;

import java.util.HashMap;

public class BaseClass {
    private final ConfigReader cr = new ConfigReader();
    



    // Method to get a RequestSpecification object with an API key
    public RequestSpecification getReqSpecWithApiKey() {
        // Creating a HashMap to hold the header information with the API key
        HashMap<String, String> header = new HashMap<String, String>() {{
            put("api-key", cr.get_api_key());
        }};
        
        // Building the RequestSpecification using RequestSpecBuilder and setting the necessary properties
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(cr.get_base_uri())
                .addHeaders(header)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
        
        return reqSpec;
    }
    
	
   

    // Method to get a RequestSpecification object without an API key
    public RequestSpecification getReqSpecWithoutApiKey() {
        // Building the RequestSpecification without the header containing the API key
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(cr.get_base_uri())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
        
        return reqSpec;
    }
    

    // Method to verify the response code received from the API call
    public void verifyResponseCode(int expectedResponseCode, Response res) {
        res.then().assertThat().statusCode(expectedResponseCode);
    }

    // Method to verify the response time of the API call
    public void verifyResponseTime(Response res, long maxResponseTime) {
        res.then()
                .assertThat()
                .time(Matchers.lessThan(maxResponseTime));
    }
}
