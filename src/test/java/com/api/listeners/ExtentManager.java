package com.api.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static ExtentReports getReporter() {
		if(extent == null) {
			String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			
			spark.config().setDocumentTitle("Baking API AutomationReport");
			spark.config().setReportName("API Test Results");
			spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			
			extent.setSystemInfo("Project", "Banking API Automation Framework");
            extent.setSystemInfo("Tester", "Raubin Kumar");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Base URL", "http://64.227.160.186:8080");
		}
		
		return extent;
	}
	
	public static void setTest(ExtentTest extentTest) {
		test.set(extentTest);
	}

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void unload() {
		test.remove();
	}
}
