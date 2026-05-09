package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.request.SignUpRequest;
import com.api.models.response.LoginResponse;

import io.restassured.response.Response;

public class AuthServiceTest {
	
	@Test(description = "Verify if SignUp API is working...")
	public void createAccountTest() {
		
		SignUpRequest signUpRequest = new SignUpRequest.Builder()
		.userName("Raubin77")
		.password("Raubin77")
		.email("raubin77@gmail.com")
		.firstName("Rahul")
		.lastName("Kumar")
		.mobileNumber("9876547890")
		.build();
		
		AuthService authService = new AuthService();
		Response response = authService.signUp(signUpRequest);
		System.out.println(response.asPrettyString());
	}
	
	@Test(description = "Verify if Login API is working...")
	public void loginTest() {
		
		LoginRequest loginRequest = new LoginRequest("Raubin","Munna@4457");
		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		
		LoginResponse loginResponse = response.as(LoginResponse.class);
		
		System.out.println(response.asPrettyString());
		System.out.println("Token : "+loginResponse.getToken());
		System.out.println("Id : "+loginResponse.getId());
		System.out.println("Email : "+loginResponse.getEmail());
		
		Assert.assertTrue(loginResponse.getToken() != null);
		Assert.assertEquals(loginResponse.getId(), 3720);
	}
	
	@Test(description = "Verify that Forgot-Password API is working...")
	public void forgotPasswordTest() {
		
		AuthService authService = new AuthService();
		Response response = authService.forgotPassword("raubinkumar4321@gmail.com");
		System.out.println(response.asPrettyString());
	}
	
}
