package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class DepositTest extends BaseTest {

	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void depositTest(String amount) throws InterruptedException {
			
		click("deposit_CSS");
		type("amount_CSS", amount);
		click("depositBtn_CSS");
		
		Thread.sleep(3000);
		
		String actMsg = driver.findElement(By.cssSelector("body > div.ng-scope > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > span")).getText();
		
		String expMsg = "Deposit Successful";
		
		Assert.assertEquals(actMsg, expMsg);
		
		Thread.sleep(3000);
		
	}
}
