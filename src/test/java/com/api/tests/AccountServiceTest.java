package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.AccountService;
import com.api.base.AuthService;
import com.api.models.request.AccountRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.AccountResponse;
import com.api.models.response.LoginResponse;

import io.restassured.response.Response;

public class AccountServiceTest {
	
	private AuthService authService;
    private AccountService accountService;
    private static String authToken;
    private static String createdAccountNumber;
    
	@BeforeClass
	public void setUp() {
		authService = new AuthService();
		accountService = new AccountService();

		Response response = authService.login(new LoginRequest("Raubin", "Munna@4457"));

		LoginResponse loginResponse = response.as(LoginResponse.class);
		authToken = loginResponse.getToken();
	}
	
	@Test(priority = 1, description = "Verify if Create Account API is working")
	public void createAccountTest() {
		
		AccountRequest accountRequest = new AccountRequest("Current Account", "North Branch");
		Response response = accountService.createAccount(authToken, accountRequest);
		
		AccountResponse accountResponse = response.as(AccountResponse.class);
        createdAccountNumber = accountResponse.getAccountNumber();
        
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(createdAccountNumber);
        
	}
	
	@Test(priority = 2, description = "Verify Get Account by Account Number is working", dependsOnMethods = "createAccountTest")
	public void getAccountByNumberTest() {
		
		Response response = accountService.getAccountByNumber(authToken, createdAccountNumber);
        AccountResponse accountResponse = response.as(AccountResponse.class);

        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(accountResponse.getAccountNumber(), createdAccountNumber);
	}

}
