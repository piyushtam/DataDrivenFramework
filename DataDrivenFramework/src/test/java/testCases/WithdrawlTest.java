package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class WithdrawlTest extends BaseTest {
	
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void withdrawlTest(String withdrawnAmount) throws InterruptedException {
		
		click("withdrawl_CSS");
		
		Thread.sleep(1000);
		
		type("withdrawnAmount_CSS", withdrawnAmount);
		
		Thread.sleep(1000);
		
		click("withdrawnBtn_CSS");
		
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("body > div.ng-scope > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > span")).getText().contains("Transaction successful"), "Transaction Failed. You can not withdraw amount more than the balance.");
		
		click("home_CSS");
		click("bmlBtn_CSS");
		
		Thread.sleep(3000);
		

		
	}

}
