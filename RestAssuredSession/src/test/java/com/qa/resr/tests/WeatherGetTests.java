package com.qa.resr.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {

	
	@Test
	public void getweatherdetails() {
		//define the base url
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		//DEFINE THE http request
		RequestSpecification httprequest = RestAssured.given();
		//make the request
		Response resp = httprequest.request(Method.GET, "/Pune");
		//get the response body
		String responsebody = resp.getBody().asString();	
		System.out.println("response body is: "+responsebody);
		//get the status code and validate it
		int statuscode = resp.getStatusCode();
		System.out.println("The status code is: "+statuscode);
		
		//validate it 
		Assert.assertEquals(responsebody.contains("Pune"), true);
		
		//get status line
		System.out.println("The status line is: "+resp.getStatusLine());
		//get the headers
		Headers headers = resp.getHeaders();
		System.out.println(headers);
		//get the specific header value
		String contentType = resp.getHeader("Content-Type");
		System.out.println("The value of the content type is: "+contentType);
		
		String contentlength = resp.getHeader("Content-Length");
		System.out.println("The value of the content length is: "+contentlength);
		
		//get the key value using jsonpath
		JsonPath jsonpathvalue = resp.jsonPath();
		Object city = jsonpathvalue.get("City");
		System.out.println("The value of city is: "+city);
		
		
		JsonPath jsonpathvaluetemp = resp.jsonPath();
		Object temp = jsonpathvaluetemp.get("Temperature");
		System.out.println("The value of temperature is: "+temp);
	}
	
	@Test
	public void getweatherdetailswithincorrectdetails() {
		//define the base url
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		//DEFINE THE http request
		RequestSpecification httprequest = RestAssured.given();
		//make the request
		Response resp = httprequest.request(Method.GET, "/test123");
		//get the response body
		String responsebody = resp.getBody().asString();	
		System.out.println("response body is: "+responsebody);
		//get the status code and validate it
		int statuscode = resp.getStatusCode();
		System.out.println("The status code is: "+statuscode);
		
		//validate it 
		Assert.assertEquals(responsebody.contains("internal error"), true);
		
}
}
