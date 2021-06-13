package testCases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class OpenAccountTest extends BaseTest {
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void openAccountTest(String customer, String currency) throws InterruptedException  {
		
		click("openAccountBtn_CSS");
		select("customer_CSS", customer);
		select("currency_CSS", currency);
		click("processBtn_CSS");
		
		Thread.sleep(3000);
		
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Account created successfully"), "Account Reg failed'");
		alert.accept();
		Thread.sleep(3000);
	
		
	}

}
