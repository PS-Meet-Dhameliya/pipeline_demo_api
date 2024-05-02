package Utility;

import init.BaseClass;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;


import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;


import static io.restassured.RestAssured.given;

public class CommonMethods extends BaseClass {

    // Request specification objects for API calls with and without API key
    private final RequestSpecification reqSpecWithApiKey;
    private final RequestSpecification reqSpecWithoutApiKey;
 
    HashMap<String, String> param = new HashMap<String, String>() {};
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();

    public CommonMethods() {
        // Initialize request specification objects using base class methods
        reqSpecWithApiKey = getReqSpecWithApiKey();
        reqSpecWithoutApiKey = getReqSpecWithoutApiKey();
       
    }

    /**
     * Method to make an API call
     *
     * @param method     HTTP method type (GET, POST, PUT, PATCH)
     * @param requestBody   Request body data for POST, PUT, PATCH methods
     * @param endpoint   API endpoint URL
     * @return Response object containing the API response
     */
    public Response callAPI(String method, List<String> requestBody, String endpoint) {
        Response res = null;
        // Set the request specification based on whether API key is required
        RequestSpecification reqSpec = reqSpecWithApiKey;
      
        
        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(requestBody)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }
        System.out.println(res.print());

        return res;
    }
    
    public Response callAPIWithJson(String method, String requestBody, String endpoint) {
        Response res = null;
        // Set the request specification based on whether API key is required
        RequestSpecification reqSpec = reqSpecWithApiKey;
      
        
        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(requestBody)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }
        return res;
    }
    
    
    
    public Response callAPIWithParam(String method, Map<String, String> testData, String endpoint,String Key,String Value) {
    	
    	 Response res = null;
         // Set the request specification based on whether API key is required
         RequestSpecification reqSpec = reqSpecWithApiKey;
         
        String endpointwithpathparam = endpoint + "?" + Key + "=" + Value;
      
        
      
        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpointwithpathparam)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(testData)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }
        return res;
    }
    
    public Response callAPIWithoutKey(String method, Map<String, String> testData, String endpoint) {
        Response res = null;
        // Set the request specification based on whether API key is required
        RequestSpecification reqSpec = reqSpecWithoutApiKey;

        if (method.equalsIgnoreCase("GET")) {
            // Send GET request and extract the response
            res = given(reqSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH")) {
            // Send POST, PUT, or PATCH request with request body data and extract the response
            res = given(reqSpec)
                    .when()
                    .body(testData)
                    .request(method, endpoint)
                    .then()
                    .extract()
                    .response();
        }

        return res;
    }



    /**
     * Method to verify the JSON schema of the API response
     *
     * @param res            Response object containing the API response
     * @param schemaFileName Name of the JSON schema file to validate against
     * @throws IOException if there is an error reading the schema file
     */
    
    
    

//    public void verifyJsonSchema(Response res, String schemaFileName) throws IOException {
//    	
//         // Read the contents of the schema file as a string
////         String jsonSchema = new String(Files.readAllBytes(file.toPath()));
//
//         // Assert that the response body matches the JSON schema
////        res.then()
////                 .assertThat()
////                 .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
//        // Get the file path of the JSON schema based on the given file name
//    	
//    	
//    	
//    	 String data = new String(Files.readAllBytes(Paths.get("src/test/java/schemas/" + schemaFileName).toAbsolutePath()));
//    
//    	
//    	 
//    	 if(data.startsWith("[")) {
//    		 StringBuilder data1 = new StringBuilder(data);
//    		 data1.insert(0,"{\"root\":");
//    		 data1.insert(data1.length(),"}");
//    		 StringBuilder data2 = new StringBuilder(res.getBody().asString());
//    		 data2.insert(0,"{\"root\":");
//    		 data2.insert(data2.length(),"}");
//    		
//    		 System.out.println(data);
//    		 System.out.println(res.getBody().asString());
//
//    		 
//    	 UnityJSONParser parser = new UnityJSONParser(data1.toString());
//    	 UnityJSONParser parser1 = new UnityJSONParser(data2.toString());
//
//    	        for (String singlePath : parser.getPathList()) {
//        	        for (String singlePath1 : parser1.getPathList()) {
//
//    	        	System.out.println(singlePath);
//    	        	System.out.println(singlePath1);
//    	        	Assert.assertEquals(singlePath1, singlePath);
//
//    	              //  Object object = JsonPath.parse(data2.toString().).read(singlePath);   
//        	        }
//    	                
//    	        }
//    	 
//    	 }
//    	 
//    	 
//    
//    	else {
//    		 
//    		
//        UnityJSONParser parser = new UnityJSONParser(data);
//        for (String singlePath : parser.getPathList()) {
//            	
//                Object object = JsonPath.parse(res.getBody().asString()).read(singlePath);   
//        }
//    	 }
//    }

    public void verifyJsonSchema(Response res, String schemaFileName) throws IOException {
    	
        // Read the contents of the schema file as a string
//        String jsonSchema = new String(Files.readAllBytes(file.toPath()));

        // Assert that the response body matches the JSON schema
//       res.then()
//                .assertThat()
//                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
       // Get the file path of the JSON schema based on the given file name
   	
   	
   	
   	 String data = new String(Files.readAllBytes(Paths.get("src/test/java/Schemas/" + schemaFileName).toAbsolutePath()));
   	
   	
   	 if(data.startsWith("[")) {
   		 StringBuilder data1 = new StringBuilder(data);
   		 data1.insert(0,"{\"root\":");
   		 data1.insert(data1.length(),"}");
   		 StringBuilder data2 = new StringBuilder(res.getBody().asString());
   		 data2.insert(0,"{\"root\":");
   		 data2.insert(data2.length(),"}");
   		
   	 UnityJSONParser parser = new UnityJSONParser(data1.toString());
   	        for (String singlePath : parser.getPathList()) {
   	            
   	                Object object = JsonPath.parse(data2.toString()).read(singlePath);   
   	        }
   	
   	 }
   	
   	
   
   	else {
   		
   		
       UnityJSONParser parser = new UnityJSONParser(data);
       for (String singlePath : parser.getPathList()) {
           	
               Object object = JsonPath.parse(res.getBody().asString()).read(singlePath);   
       }
   	 }
   }
}
