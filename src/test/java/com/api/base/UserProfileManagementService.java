package com.api.base;

import com.api.models.request.ChangePasswordRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.request.UpdateProfileRequest;

import io.restassured.response.Response;

public class UserProfileManagementService extends BaseService {
	
	private static final String BASE_PATH = "/api/users/";
	
	public Response getProfile(String token) {
		setAuthToken(token);
		return getRequest(BASE_PATH + "profile");
	}
	
	public Response updateProfile(String token, ProfileRequest payload) {
		setAuthToken(token);
		return putRequest(payload, BASE_PATH + "profile");
	}
	
	public Response patchProfile(String token, UpdateProfileRequest payload) {
        setAuthToken(token);
        return patchRequest(payload, BASE_PATH + "profile");
    }

    public Response changePassword(String token, ChangePasswordRequest payload) {
        setAuthToken(token);
        return putRequest(payload, BASE_PATH + "change-password");
    }
    
}
