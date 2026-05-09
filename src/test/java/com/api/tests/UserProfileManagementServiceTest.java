package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.ChangePasswordRequest;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.request.UpdateProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;

import io.restassured.response.Response;

public class UserProfileManagementServiceTest {

	private AuthService authService;
	private UserProfileManagementService userProfileManagementService;
	private static String authToken;

	@BeforeClass
	public void setUp() {
		authService = new AuthService();
		userProfileManagementService = new UserProfileManagementService();

		Response response = authService.login(new LoginRequest("Raubin", "Munna@4457"));

		LoginResponse loginResponse = response.as(LoginResponse.class);
		authToken = loginResponse.getToken();
	}

	@Test(priority = 1, description = "Verify if get Profile API is working...")
	public void getProfileInfoTest() {

		Response response = userProfileManagementService.getProfile(authToken);
		System.out.println(response.asPrettyString());

		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		System.out.println(userProfileResponse.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(userProfileResponse.getUsername());
	}

	@Test(priority = 2, description = "Verify if Update Profile API is working...")
	public void updateProfileTest() {

		Response response = userProfileManagementService.getProfile(authToken);
		System.out.println(response.asPrettyString());

		System.out.println("----------------------------------------------------------");

		ProfileRequest profileRequest = new ProfileRequest.Builder().email("raubinkumar@gmail.com").lastName("Kumar")
				.firstName("Raubin").mobileNumber("9178543276").build();

		response = userProfileManagementService.updateProfile(authToken, profileRequest);
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3, description = "Verify if partial update Profile API is working...")
	public void partialUpdateProfileTest() {

		UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest.Builder().address("Patna, Bihar").build();

		Response response = userProfileManagementService.patchProfile(authToken, updateProfileRequest);

		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4, description = "Verify if Change Password API is working")
	public void changePasswordTest() {

		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("Munna@4457", // currentPassword
				"Munna@4458", // newPassword
				"Munna@4458" // confirmPassword
		);

		Response response = userProfileManagementService.changePassword(authToken, changePasswordRequest);

		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@AfterClass
	public void tearDown() {
		// Revert password back to original so other test classes are not affected
		ChangePasswordRequest revertPassword = new ChangePasswordRequest(
				"Munna@4458", // currentPassword (changed in changePasswordTest)																
				"Munna@4457", // newPassword (original)
				"Munna@4457" // confirmPassword
		);
		userProfileManagementService.changePassword(authToken, revertPassword);
	}
}
