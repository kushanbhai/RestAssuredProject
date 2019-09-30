package com.qa.resr.tests;

import org.testng.annotations.Test;

import com.qa.rest.objects.CustomerResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@Test
public class CreatePOSTCALLPojo {

	
	public void createcustomerTest() {

        //define the BASE URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		// DEFINE THE http request
		RequestSpecification httprequest = RestAssured.given();
		//create a JSON object with all the required fields
		org.json.simple.JSONObject reqjson = new org.json.simple.JSONObject();
		reqjson.put("FirstName", "ramesh1");
		reqjson.put("LastName", "verma1");
		reqjson.put("UserName", "raverm12");
		reqjson.put("Email", "ramwerma1@gmail.com");
		reqjson.put("Password", "ramverm1234");
		
		//add the header
		httprequest.header("Content-Type","application/json");
		
		//create the payload to the body pf the request
		httprequest.body(reqjson.toJSONString());
		
		//post the request and get d response
		Response resp = httprequest.post("/register");
		
		
		//get the response body
		String respbody = resp.getBody().asString();
		System.out.println("response body is: " + respbody);
		//deserializing the response
		CustomerResponse customresp = resp.as(CustomerResponse.class);
		System.out.println(customresp.SuccessCode);
		System.out.println(customresp.Message);
		Assert.assertEquals("OPERATION_SUCCESS", customresp.SuccessCode);
		Assert.assertEquals("Operation completed successfully", customresp.Message);
}
}
