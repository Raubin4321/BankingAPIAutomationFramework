package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.AccountService;
import com.api.base.AuthService;
import com.api.models.request.AccountRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.BankAccountResponse;
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
	public void createBankAccountTest() {

		AccountRequest accountRequest = new AccountRequest("Current Account", "North Branch");
		Response response = accountService.createAccount(authToken, accountRequest);

		BankAccountResponse accountResponse = response.as(BankAccountResponse.class);
		createdAccountNumber = accountResponse.getAccountNumber();

		System.out.println(response.asPrettyString());
		//Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertNotNull(createdAccountNumber);

	}

	@Test(priority = 2, description = "Verify Get Account by Account Number is working", dependsOnMethods = "createBankAccountTest")
	public void getBankAccountByNumberTest() {

		Response response = accountService.getAccountByNumber(authToken, createdAccountNumber);
		BankAccountResponse accountResponse = response.as(BankAccountResponse.class);

		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(accountResponse.getAccountNumber(), createdAccountNumber);
	}

}
