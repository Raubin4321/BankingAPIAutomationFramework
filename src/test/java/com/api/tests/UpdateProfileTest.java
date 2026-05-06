package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.response.LoginResponse;

import io.restassured.response.Response;

public class UpdateProfileTest {
	
	@Test(description = "Verify if Update Profile API is working...")
	public void updateProfileTest() {
		
		AuthService authService = new AuthService();
		Response response = authService.login(new LoginRequest("Raubin","Munna@4457"));
		System.out.println(response.asPrettyString());
		
		System.out.println("----------------------------------------------------------");
		
		LoginResponse loginResponse = response.as(LoginResponse.class);
		
		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
		response = userProfileManagementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		
		System.out.println("----------------------------------------------------------");
		
		ProfileRequest profileRequest = new ProfileRequest.Builder()
		.email("raubinkumar4321@gmail.com")
		.lastName("Sahni")
		.firstName("Raubin")
		.mobileNumber("9685354457")
		.build();
		
		response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
		System.out.println(response.asPrettyString());
	}

}
