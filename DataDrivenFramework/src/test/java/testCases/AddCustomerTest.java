package testCases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class AddCustomerTest extends BaseTest{

	
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(String firstName, String lastName, String postCode) throws InterruptedException  {
		
		click("addCustomer_CSS");
		type("firstName_CSS",firstName);
		type("lastName_CSS",lastName);
		type("postCode_CSS",postCode);
		click("addCustBtn_CSS");
		
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Customer added successfully"),"Customer not added successfully");
		alert.accept();
		
		
		Thread.sleep(3000);
		
	}
}
