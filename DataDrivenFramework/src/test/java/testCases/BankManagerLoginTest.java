package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest  {
	
	@Test
	public void loginAsBankManager() {
		
		click("bmlBtn_CSS");
		
		Assert.assertTrue(isElementPresent("addCustomer_CSS"),"Test failed as BankManager was not successfully logged in !!");
	}
	
	

}
