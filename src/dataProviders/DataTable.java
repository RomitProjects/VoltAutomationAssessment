package dataProviders;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.*;

public class DataTable {
	@DataProvider (name = "data-provider")
    public Object[][] dpMethod(Method m){
		
		//Item List
		List<String> Items = new ArrayList<String>();
		Items.add("Sauce Labs Backpack");
		Items.add("Sauce Labs Bolt T-Shirt");
		Items.add("Sauce Labs Bike Light");
		
		switch (m.getName()) {
		case "LoginTest":
			return new Object[][] {{"standard_user","secret_sauce"}};
		case "LoginWithInvalidUser":
			return new Object[][] {{"Epic sadface: Username and password do not match any user in this service"}};
		case "LoginWithMissingUsername":
			return new Object[][] {{"Epic sadface: Username is required"}};
		case "LoginWithMissingPassword":
			return new Object[][] {{"Epic sadface: Password is required"}};
		case "CartFunctionality":
			return new Object[][] {{"standard_user","secret_sauce"}};
		case "CompleteCheckOutFlow":
			return new Object[][] {{"standard_user","secret_sauce",Items}};
		}
		return null;
    } 
}
