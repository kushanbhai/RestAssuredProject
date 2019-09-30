package com.qa.resr.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateCustomerPostCall {

	@Test
	public void createcustomerTest() {

        //define the BASE URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		// DEFINE THE http request
		RequestSpecification httprequest = RestAssured.given();
		//create a JSON object with all the required fields
		org.json.simple.JSONObject reqjson = new org.json.simple.JSONObject();
		reqjson.put("FirstName", "kushan2");
		reqjson.put("LastName", "choudhury2");
		reqjson.put("UserName", "kuchou12");
		reqjson.put("Email", "kuchou1@gmail.com");
		reqjson.put("Password", "test123456");
		
		//add the header
		httprequest.header("Content-Type","application/json");
		
		//create the payload to the body pf the request
		httprequest.body(reqjson.toJSONString());
		
		//post the request and get d response
		Response resp = httprequest.post("/register");
		
		
		//get the response body
		String respbody = resp.getBody().asString();
		System.out.println("response body is: " + respbody);
		
		
	}
}
