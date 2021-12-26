package com.employeeAPI.testCases;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.Base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends BaseTest{

	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("********* Started TC001_Get_All_Employees **********");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody()
	{
		logger.info("********* Checking response body *******************");

		String responseBody = response.body().asString();
		logger.info("Response body => " + responseBody);
		Assert.assertTrue(responseBody!=null);	}

	@Test
	void checkStatuscode()
	{
		logger.info("********* Checking response status code *******************");

		int statusCode = response.getStatusCode();
		logger.info("Response Status => " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime()
	{
		logger.info("********* Checking response time *******************");

		long responseTime = response.getTime();
		logger.info("Response Status => " + responseTime);
		if(responseTime >10000) {
			logger.warn("Response time is grater than 10000");
		}
		Assert.assertTrue(responseTime<10000);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("********* Checking status line *******************");
		String statusLine = response.statusLine();
		logger.info("Status Line => " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("********* Checking Content Type *******************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type => " + contentType);
		Assert.assertEquals(contentType, "application/json");	
	}
	
	@Test
	void checkServerType()
	{
		logger.info("********* Checking Server Type *******************");
		String serverType = response.header("Server");
		logger.info("Server type => " + serverType);
		Assert.assertEquals(serverType, "nginx");
	}
	
	@Test
	void checkContentLength()
	{
		logger.info("********* Checking Content length *******************");
		String contentLength = response.header("Content-Length");
		logger.info("Content length => " + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength)<800);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_Get_All_Employees **********");
	}
		
}
