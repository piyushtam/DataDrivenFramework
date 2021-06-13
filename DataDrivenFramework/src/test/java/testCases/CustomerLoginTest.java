package testCases;

import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class CustomerLoginTest extends BaseTest {
	
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void customerLoginTest(String yourName) throws InterruptedException {
		
		click("home_CSS");
		click("customerLoginBtn_CSS");
		select("yourName_CSS", yourName);
		click("loginbtn_CSS");
		
		Thread.sleep(3000);
	}
	

}
