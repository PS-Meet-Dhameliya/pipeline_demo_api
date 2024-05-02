package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utility.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetAPIsteps  {
	
	
	
	CommonMethods cm;
	 Response response;
	 List<String> dataList;

	

	

		 @Given("user has endpoint {string}")
		 public void user_has_endpoint(String endpoint) {
			 
			 cm = new CommonMethods();
//				
				response = cm.callAPI("GET", null, endpoint);
		 }
		 @Then("user get successful response")
		 public void user_get_successful_response() throws IOException {
			 cm.verifyJsonSchema(response, "response.json");
		 }
		 @When("status code is {string}")
		 public void status_code_is(String string) {
			 cm.verifyResponseCode(200, response);
		 }
		 @When("response time must be less as expected")
		 public void response_time_must_be_less_as_expected() {
			 cm.verifyResponseTime(response, 6000);
		 }
		 
		 @Given("user has wrong endpoint {string}")
		    public void user_has_wrong_endpoint(String endpoint) {
			 cm = new CommonMethods();
			response = cm.callAPI("GET",null, endpoint);
		    }

		    @Then("user get bad request")
		    public void user_get_bad_request() {
		        // You can add additional checks here if needed
		    }

		    @When("status new code is {string}")
		    public void status_new_code_is(String string) {
		    	cm.verifyResponseCode(404, response);
		    }

		   //Post
		    
		    @Given("user has post endpoint {string}")
		    public Response user_has_post_endpoint(String endpoint) {
		    	
		    	cm = new CommonMethods();
		    	dataList = new ArrayList<>();
		        
		     dataList.add("EU-WORKER_CARC_MUTA-ANX_I_ART_2");
		     dataList.add("EU-ECOLABEL-ART_6_6");
		     	response = cm.callAPI("POST", dataList, endpoint);
		     	
		     	System.out.println(dataList);
		     	return response;
		    }

		    @Then("user has successful body response")
		    public void user_has_successful_body_response() throws IOException {
			       System.out.println(response);
		    	cm.verifyJsonSchema(response, "post1.json");
		    }

		    @When("status code for post is {string}")
		    public void status_code_for_post_is(String statusCode) {
		    	cm.verifyResponseCode(200, response);
		    }

		    @When("response time less then expected")
		    public void response_time_less_then_expected() {
		    	cm.verifyResponseTime(response, 5000);
		    }
		    
		    @Given("user give get endpoint {string} and give params {string} and {string}")
		    public void user_give_get_endpoint_and_give_params(String endpoint, String value, String key) {
		        cm = new CommonMethods();
		        response = cm.callAPIWithParam("GET", null, endpoint, key, value);
		    }

		    @Then("user get successful params response")
		    public void user_get_successful_params_response() throws IOException {
		    	cm.verifyJsonSchema(response, "update.json");
		    	
		    }

		    @When("user get status code is {string}")
		    public void user_get_status_code_is(String statusCode) {
		    	cm.verifyResponseCode(200, response);
		    }

		    @When("user get response in limited time")
		    public void user_get_response_in_limited_time() {
		    	cm.verifyResponseTime(response, 5000);
		    }
		    
		    @Given("user enter wrong post method endpoint {string}")
		    public void user_enter_wrong_post_method_endpoint(String endpoint) {
		        cm = new CommonMethods();
		        dataList = new ArrayList<>();
		        
		        response = cm.callAPI("POST", dataList, endpoint);
		    }

		    @Then("user not get any response body")
		    public void user_not_get_any_response_body() {
		    }

		    @When("user received a status code {string}")
		    public void user_received_a_status_code(String statusCode) {
		    	cm.verifyResponseCode(404, response);
		    }

		    @When("response time is less")
		    public void response_time_is_less() {
		    	cm.verifyResponseTime(response, 1000);
		    }
		    
		    @Given("user enter the endpoint {string}")
		    public void user_enter_the_endpoint(String endpoint) {
		    }

		    @Then("user not received any response")
		    public void user_not_received_any_response() {
		    }

		    @When("user get the response code with {string}")
		    public void user_get_the_response_code_with(String statusCode) {
		    }

		    @When("the response time is lessthen expected")
		    public void the_response_time_is_lessthen_expected() {
		    }
	}














