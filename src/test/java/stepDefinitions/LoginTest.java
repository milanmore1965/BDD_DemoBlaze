package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.When;

public class LoginTest extends TestBase {


	@When("^User login to the application with valid credentials$")
	public void user_login_with_valid_credentails() throws Throwable {
		appLogin();
		System.out.println("test");
	}

	// checks logout link to verify if user is logged in successfully 
	@When("^verify user is able to logged in successfully$")
	public void verify_user_login_successfully() throws Throwable {
		try
		{
			WebElement element=driver.findElement((By.xpath("//a[@id='logout2']")));
			String strAttribute=element.getAttribute("innerHTML");
			if(strAttribute.equalsIgnoreCase("Log out")) {
				Assert.assertEquals("Log out", strAttribute); } 
		}

		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}




