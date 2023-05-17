package testScripts;
import objRepository.*;
import dataProviders.*;

import org.testng.Assert;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestCases {
	WebDriver driver;
	LoginPage loginPage;
	CatalogPage catalogPage;
	Cart_CheckoutPage cartCheckoutPage;
  
  @BeforeMethod
  public void Setup() {
	  System.setProperty("webdriver.chrome.driver","C:/Users/CEPL/Dependencies/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/"); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @Test(enabled=true,priority=3,dataProvider = "data-provider", dataProviderClass = DataTable.class)
  public void LoginTest(String user,String pass)
  {
	  loginPage = new LoginPage();
	  String url = loginPage.loginToSite(driver, user, pass);
	  Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html","Login Failed");
		
  }
  
  @Test(priority=3,dataProvider = "data-provider", dataProviderClass = DataTable.class)
  public void LoginWithInvalidUser(String expectedErrMsg)
  {
	  loginPage = new LoginPage();
	  String errorMsg = loginPage.invalidLogin(driver);
	  Assert.assertEquals(errorMsg, expectedErrMsg,"Test Failed");
		
  }
  
  @Test(priority=4,dataProvider = "data-provider", dataProviderClass = DataTable.class)
  public void LoginWithMissingUsername(String expectedErrMsg)
  {
	  loginPage = new LoginPage();
	  String errorMsg = loginPage.missingUsernameLogin(driver);
	  System.out.print(errorMsg);
	  Assert.assertEquals(errorMsg, expectedErrMsg,"Test Failed");	
  }
  
  @Test(priority=5,dataProvider = "data-provider", dataProviderClass = DataTable.class)
  public void LoginWithMissingPassword(String expectedErrMsg)
  {
	  loginPage = new LoginPage();
	  String errorMsg = loginPage.missingPasswordLogin(driver);
	  System.out.print(errorMsg);
	  Assert.assertEquals(errorMsg, "Epic sadface: Password is required","Test Failed");
		
  }
  
  @Test(priority=1,dataProvider = "data-provider", dataProviderClass = DataTable.class)
  public void CartFunctionality(String user, String pass)
  {
	  loginPage = new LoginPage();
	  catalogPage = new CatalogPage();
	  loginPage.loginToSite(driver, user, pass);
	  Assert.assertTrue(catalogPage.checkSiteTitle(driver),"Login failed");
	  Assert.assertTrue(catalogPage.AddItemtoCartfromCatalogPage(driver),"Item Not Added to Cart");
	  Assert.assertTrue(catalogPage.RemoveItemtoCartfromCatalogPage(driver),"Item Not Removed from Cart");
	  Assert.assertTrue(catalogPage.AddItemtoCartfromProductPage(driver),"Item Not Added to Cart");
	  	  
  }
  
  @Test(priority=2,dataProvider = "data-provider", dataProviderClass = DataTable.class)
  public void CompleteCheckOutFlow(String user, String pass, List<String> Items)
  {
	  loginPage = new LoginPage();
	  catalogPage = new CatalogPage();
	  cartCheckoutPage = new Cart_CheckoutPage();
	  loginPage.loginToSite(driver, user, pass);
	  Assert.assertTrue(catalogPage.checkSiteTitle(driver),"Login failed");
	  for (String element : Items) {
		  Assert.assertTrue(catalogPage.addCustomItem(driver, element),"Item was not added");
		}
	  catalogPage.openCart(driver);
	  Assert.assertTrue(cartCheckoutPage.checkSiteTitle(driver), "Cart was not opened");
	  Assert.assertTrue(cartCheckoutPage.checkCartItems(driver, Items),"Cart Items are incorrect");
	  Assert.assertTrue(cartCheckoutPage.CheckoutBtnClick(driver),"Checkout Btn not clicked properly");
	  Assert.assertTrue(cartCheckoutPage.CheckoutFormFlow(driver, "test", "user", "203904"),"Checkout form not filled properly");
	  Assert.assertTrue(cartCheckoutPage.subtotalCheck(driver),"SubTotal is incorrect");
	  Assert.assertTrue(cartCheckoutPage.totalCheck(driver),"Total is incorrect");
	  Assert.assertTrue(cartCheckoutPage.finishCheckout(driver),"Checkout incomplete");
	  
	    
	  
  }
  
  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  Thread.sleep(1000);
	  driver.close();
  }

}
