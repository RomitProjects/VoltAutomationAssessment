package objRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CatalogPage {

	By PageTitle = By.xpath("//span[@class='title' and text()='Products']");
	By AddtoCart_Onesie = By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']");
	By RemovefromCart_Onesie = By.xpath("//button[@id='remove-sauce-labs-onesie']");
	By AddtoCart_Backpack = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
	By ShoppingCartBadge = By.xpath("//span[@class='shopping_cart_badge']");
	By TShirtItem = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Bolt T-Shirt']");
	By AddtoCart_Tshirt = By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']");
	By ShoppingCart = By.xpath("//a[@class='shopping_cart_link']");
	
	
	public boolean checkSiteTitle (WebDriver driver)
	{
		if(!driver.findElements(PageTitle).isEmpty())
		{	
		return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean AddItemtoCartfromCatalogPage (WebDriver driver)
	{
		int cartnum1;

		if(!driver.findElements(ShoppingCartBadge).isEmpty())
		{
		String cartNumStr1 = driver.findElement(ShoppingCartBadge).getText();
		cartnum1 = Integer.valueOf(cartNumStr1);
		}
		else
		{
		cartnum1 = 0;	
		}
		driver.findElement(AddtoCart_Onesie).click();
		String cartNumStr2 = driver.findElement(ShoppingCartBadge).getText();
		int cartnum2 = Integer.valueOf(cartNumStr2);
	
		if(cartnum2==(cartnum1+1))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public boolean RemoveItemtoCartfromCatalogPage (WebDriver driver)
	{
		String cartNumStr1 = driver.findElement(ShoppingCartBadge).getText();
		int cartnum1 = Integer.valueOf(cartNumStr1);
		driver.findElement(RemovefromCart_Onesie).click();
		int cartnum2;
		if(!driver.findElements(ShoppingCartBadge).isEmpty())
		{
		String cartNumStr2 = driver.findElement(ShoppingCartBadge).getText();
		cartnum2 = Integer.valueOf(cartNumStr2);
		}
		else
		{
			cartnum2 = 0;
		}
	
		if(cartnum2==(cartnum1-1))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean AddItemtoCartfromProductPage (WebDriver driver)
	{
		driver.findElement(TShirtItem).click();
		int cartnum1;

		if(!driver.findElements(ShoppingCartBadge).isEmpty())
		{
		String cartNumStr1 = driver.findElement(ShoppingCartBadge).getText();
		cartnum1 = Integer.valueOf(cartNumStr1);
		}
		else
		{
		cartnum1 = 0;	
		}
	
		driver.findElement(AddtoCart_Tshirt).click();
		String cartNumStr2 = driver.findElement(ShoppingCartBadge).getText();
		int cartnum2 = Integer.valueOf(cartNumStr2);
	
		if(cartnum2==(cartnum1+1))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean addCustomItem(WebDriver driver, String Item)
	{
		int cartnum1;
		

		if(!driver.findElements(ShoppingCartBadge).isEmpty())
		{
		String cartNumStr1 = driver.findElement(ShoppingCartBadge).getText();
		cartnum1 = Integer.valueOf(cartNumStr1);
		}
		else
		{
		cartnum1 = 0;	
		}
		
		String xpath = "//div[text()='"+Item+"']/ancestor::div[@class='inventory_item_description']//button";
				
		driver.findElement(By.xpath(xpath)).click();
		
		String cartNumStr2 = driver.findElement(ShoppingCartBadge).getText();
		int cartnum2 = Integer.valueOf(cartNumStr2);
	
		if(cartnum2==(cartnum1+1))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	public void openCart(WebDriver driver)
	{
		driver.findElement(ShoppingCart).click();
	}
	
	
}
