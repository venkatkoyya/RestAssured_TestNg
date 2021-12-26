package com.employeeAPI.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class EmployeeRecord {

	public static String empName()
	{
		String name = RandomStringUtils.randomAlphabetic(1);
		return ("Venkat" + name) ;
	}
	
	public static String empSal()
	{
		String sal = RandomStringUtils.randomNumeric(5);
		return sal;
	}
	
	public static String empAge()
	{
		String age = RandomStringUtils.randomNumeric(2);
		return age;
	}
}
