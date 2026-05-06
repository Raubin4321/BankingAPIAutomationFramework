package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationTest {
	
	@Test(description = "Verify if SignUp API is working...")
	public void createAccountTest() {
		
		SignUpRequest signUpRequest = new SignUpRequest.Builder()
		.userName("Raubin07")
		.password("Raubin123")
		.email("kumarraubin05@gmail.com")
		.firstName("Raubin")
		.lastName("Kumar")
		.mobileNumber("9685354457")
		.build();
		
		AuthService authService = new AuthService();
		Response response = authService.signUp(signUpRequest);
		System.out.println(response.asPrettyString());
	}

}
