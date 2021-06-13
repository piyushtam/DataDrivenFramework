package testCases;

import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class CustomersTest extends BaseTest {

	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void customersTest(String customerList) throws InterruptedException {
		
		click("customersBtn_CSS");
		type("searchCust_CSS", customerList);
		click("deleteCust_CSS");
		
		Thread.sleep(2000);
		
		clear("cusList_CSS");
		
	}
}
