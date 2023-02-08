package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class demo {

	/*
	 * @BeforeSuite public void beforeSuite() { System.out.println("beforeSuite"); }
	 * 
	 * @AfterSuite public void afterSuite() { System.out.println("afterSuite"); }
	 * 
	 * @BeforeClass public void beforeClass() { System.out.println("beforeClass"); }
	 * 
	 * @AfterClass public void afterClass() { System.out.println("afterClass"); }
	 * 
	 * @BeforeTest public void beforeTest() { System.out.println("beforeTest"); }
	 * 
	 * @AfterTest public void afterTest() { System.out.println("afterTest"); }
	 * 
	 * @BeforeMethod public void beforeMethod() {
	 * System.out.println("beforeMethod"); }
	 * 
	 * @AfterMethod public void afterMethod() { System.out.println("afterMethod"); }
	 */

	@Test(priority = 1)
	public void testM() {
		System.out.println("TEst");
		//Assert.assertEquals(false, true);
	}

	@Test(priority = 0)
	@Parameters("userName")
	public void testM2() {
		System.out.println("TestM2");
	}
	
	@Test(dataProvider = "loginData")
	public void M3() {
		System.out.println("M3");
	}
	
	@DataProvider(name = "loginData")
	public Object getLoginData() {
		
		return new Object[][]{{"Mayank", "Gutpa"}, {"A","V"}};
	}

}
