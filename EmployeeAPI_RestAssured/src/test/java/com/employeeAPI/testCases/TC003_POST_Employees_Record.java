package com.employeeAPI.testCases;


import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.Base.BaseTest;
import com.employeeAPI.utils.EmployeeRecord;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_POST_Employees_Record extends BaseTest{
	
	public String emp_name = EmployeeRecord.empName();
	public String emp_age = EmployeeRecord.empAge();
	public String emp_sal = EmployeeRecord.empSal();

	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("********* Started TC003_POST_Employees_Record **********");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		HashMap<String, String> employeeRecord = new HashMap<String,String>();
		employeeRecord.put("name",emp_name);
		employeeRecord.put("age",emp_age);
		employeeRecord.put("salary",emp_sal);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(employeeRecord);
		response = httpRequest.request(Method.POST,"/create");
		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody()
	{
		logger.info("********* Checking response body *******************");

		String responseBody = response.body().asString();
		logger.info("Response body => " + responseBody);
		Assert.assertEquals(responseBody.contains(emp_name), true);
		Assert.assertEquals(responseBody.contains(emp_age), true);
		Assert.assertEquals(responseBody.contains(emp_sal), true);
	}

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
		logger.info("*********  Finished TC003_POST_Employees_Record **********");
	}
		
}
