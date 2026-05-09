package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListener.class) ;
	
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started!!!");
		
		ExtentManager.getReporter();
	}
	
	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed!!!");
		
		ExtentManager.getReporter().flush();
		ExtentManager.unload();
	}
	
	public void onTestStart(ITestResult result) {
		logger.info("Test Started : " + result.getMethod().getMethodName());
		logger.info("Description : " + result.getMethod().getDescription());
		
		ExtentTest test = ExtentManager.getReporter().createTest(result.getMethod().getMethodName());

		test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
		test.info("Test Started: " + result.getMethod().getDescription());

		ExtentManager.setTest(test);
	}
	
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed : " + result.getMethod().getMethodName());
		
		ExtentManager.getTest().pass("Test Passed ✅");
	}
	
	public void onTestFailure(ITestResult result) {
		logger.error("Test Failed : " + result.getMethod().getMethodName());
		
		ExtentTest test = ExtentManager.getTest();

		test.fail("Test Failed ❌");
		test.fail(result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		logger.info("Test Skipped : " + result.getMethod().getMethodName());
		
		ExtentManager.getTest().skip("Test Skipped ⚠️" + result.getThrowable());
	}

}
