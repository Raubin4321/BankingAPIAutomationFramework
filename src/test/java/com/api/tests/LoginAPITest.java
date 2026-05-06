package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;

import io.restassured.response.Response;

@Listeners(com.api.listeners.TestListener.class)
public class LoginAPITest {
	
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
		Assert.assertEquals(loginResponse.getEmail(), "raubinkumar4321@gmail.com");
		
	}

}
