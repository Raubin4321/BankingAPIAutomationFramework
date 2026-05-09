package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.listeners.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		logRequest(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec); //Request is going to executed
		logResponse(response);
		return response;
	}
	
	public void logRequest(FilterableRequestSpecification requestSpec) {
		logger.info("BASE URI : "+ requestSpec.getBaseUri());
		logger.info("Request Header : "+ requestSpec.getHeaders());
		logger.info("Request Payload : "+ requestSpec.getBody());
		
		// ExtentReport
		ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.info("<details>"
                    + "<summary><b>Request Details (click to expand)</b></summary>"
                    + "<pre>"
                    + "URI     : " + requestSpec.getBaseUri() + requestSpec.getDerivedPath() + "\n"
                    + "Method  : " + requestSpec.getMethod() + "\n"
                    + "Headers : " + requestSpec.getHeaders() + "\n\n"
                    + "Payload : \n" + formatJson(requestSpec.getBody())
                    + "</pre>"
                    + "</details>");
        }
		
	}
	

	public void logResponse(Response response) {
		logger.info("STATUS CODE : "+ response.getStatusCode());
		logger.info("Response Header : "+ response.headers());
		logger.info("Response Body : "+ response.getBody().prettyPrint());
		
		// ExtentReport
		ExtentTest test = ExtentManager.getTest();
        if (test != null) {
            test.info("<details>"
                    + "<summary><b>Response Details (click to expand)</b></summary>"
                    + "<pre>"
                    + "Status Code  : " + response.getStatusCode()  + "\n"
                    + "Response Time: " + response.getTime() + " ms\n"
                    + "Headers      : " + response.getHeaders()      + "\n\n"
                    + "Body:\n"         + response.asPrettyString()
                    + "</pre>"
                    + "</details>");
        }
	}
	
	// Helper to pretty print request body 
	private String formatJson(Object body) {
		if (body == null) 
			return "No Payload";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(body.toString(), Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (Exception e) {
            return body.toString(); // return as-is if not JSON
        }
	}

}
