package com.api.base;

import com.api.models.request.AccountRequest;

import io.restassured.response.Response;

public class AccountService extends BaseService {
	private static final String BASE_PATH = "/api/accounts";
	
	public Response createAccount(String token, AccountRequest payload) {
        setAuthToken(token);
        return postRequest(payload, BASE_PATH);
    }
	
	public Response getAccountByNumber(String token, String accountNumber) {
        setAuthToken(token);
        return getRequest(BASE_PATH + "/" + accountNumber);
    }
	
}
