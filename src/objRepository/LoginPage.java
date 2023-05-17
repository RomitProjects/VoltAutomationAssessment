package objRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	By userName = By.xpath("//input[@placeholder='Username']");
	By passWord = By.xpath("//input[@placeholder='Password']");
	By logIn = By.xpath("//input[@id='login-button']");
	By errorMsgContainer = By.xpath("//div[@class='error-message-container error']/h3");
			
	public String loginToSite (WebDriver driver, String user, String pass)
	{
		driver.findElement(userName).sendKeys(user);
		driver.findElement(passWord).sendKeys(pass);
		driver.findElement(logIn).click();
		String url = driver.getCurrentUrl();
		
		return url;
	}
	
	public String invalidLogin (WebDriver driver)
	{
		driver.findElement(userName).sendKeys("test1");
		driver.findElement(passWord).sendKeys("test1");
		driver.findElement(logIn).click();
		String msg = driver.findElement(errorMsgContainer).getText();
		
		return msg;
	}
	
	public String missingPasswordLogin (WebDriver driver)
	{
		driver.findElement(userName).sendKeys("test1");
		driver.findElement(logIn).click();
		String msg = driver.findElement(errorMsgContainer).getText();
		
		return msg;
	}
	
	public String missingUsernameLogin (WebDriver driver)
	{
		driver.findElement(passWord).sendKeys("test1");
		driver.findElement(logIn).click();
		String msg = driver.findElement(errorMsgContainer).getText();
		
		return msg;
	}
	
	public String lockedUserLogin (WebDriver driver, String user, String pass)
	{
		driver.findElement(userName).sendKeys(user);
		driver.findElement(passWord).sendKeys(pass);
		driver.findElement(logIn).click();
		String msg = driver.findElement(errorMsgContainer).getText();
		
		return msg;
	}
	
	
	
}
