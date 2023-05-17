package objRepository;
import java.util.*;
import java .util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart_CheckoutPage {

	By CartTitle = By.xpath("//span[@class='title' and text()='Your Cart']");
	By CheckoutBtn = By.xpath("//button[@id='checkout']");
	By CartItems = By.xpath("//div[@class='inventory_item_name']");
	By CheckoutInfoTitle = By.xpath("//span[text()='Checkout: Your Information']");
	By FirstName = By.xpath("//input[@id='first-name']");
	By LastName = By.xpath("//input[@id='last-name']");
	By PostalCode = By.xpath("//input[@id='postal-code']");
	By Continue = By.xpath("//input[@id='continue']");
	By CheckoutOverviewTitle = By.xpath("//span[text()='Checkout: Overview']");
	By ItemTotals = By.xpath("//div[@class='inventory_item_price']");
	By SubTotal = By.xpath("//div[@class='summary_subtotal_label']");
	By Tax = By.xpath("//div[@class='summary_tax_label']");
	By GTotal = By.xpath("//div[@class='summary_info_label summary_total_label']");
	By FinishBtn = By.xpath("//button[@id='finish']");
	By CheckOutCompleteTitle = By.xpath("//span[@class='title' and text()='Checkout: Complete!']");
	
	
	
	public boolean checkSiteTitle (WebDriver driver)
	{
		if(!driver.findElements(CartTitle).isEmpty())
		{	
		return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkCartItems(WebDriver driver, List<String> Items)
	{
		List<WebElement> ItemElements = driver.findElements(CartItems);
		List<String> ItemElements_Text = new ArrayList<String>();
		for(WebElement e : ItemElements){
			ItemElements_Text.add(e.getText());
		}
		Collections.sort(Items);
		Collections.sort(ItemElements_Text);
		if(Items.equals(ItemElements_Text))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean CheckoutBtnClick (WebDriver driver)
	{
		driver.findElement(CheckoutBtn).click();
		if(!driver.findElements(CheckoutInfoTitle).isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean CheckoutFormFlow(WebDriver driver,String fname, String lname, String postal)
	{
		
		driver.findElement(FirstName).sendKeys(fname);
		driver.findElement(LastName).sendKeys(lname);
		driver.findElement(PostalCode).sendKeys(postal);
		driver.findElement(Continue).click();
		
		if(!driver.findElements(CheckoutOverviewTitle).isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public boolean subtotalCheck(WebDriver driver)
	{
		int  totalItems = driver.findElements(ItemTotals).size();
		float Total=0.0f;
		for(int i=0;i<totalItems;i++)
		{
		String strItemTotal = driver.findElements(ItemTotals).get(i).getText();
		float price = Float.valueOf(strItemTotal.substring(1));
		//System.out.println(price);
		Total=Total+price;
		}
		//System.out.println(Total);
		
		String strSubTotal = driver.findElement(SubTotal).getText().substring(13);
		float subTotal = Float.valueOf(strSubTotal);
		
		if(subTotal==Total)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		
	public boolean totalCheck(WebDriver driver)
	{
		String strSubTotal = driver.findElement(SubTotal).getText().substring(13);
		float subTotal = Float.valueOf(strSubTotal);
		
		String strTax = driver.findElement(Tax).getText().substring(6);
		float Tax = Float.valueOf(strTax);
		
		String strTotal = driver.findElement(GTotal).getText().substring(8);
		float Total = Float.valueOf(strTotal);
		
		float calculatedTotal = subTotal + Tax;
		
		if(calculatedTotal==Total)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		
		
	public boolean finishCheckout(WebDriver driver)
	{
			driver.findElement(FinishBtn).click();
			
			if(!driver.findElements(CheckOutCompleteTitle).isEmpty())
			{	
			return true;
			}
			else
			{
				return false;
			}
	}
	
	}
	
	
	
		
	
	
	
	


