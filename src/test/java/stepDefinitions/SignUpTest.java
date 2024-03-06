package stepDefinitions;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ScreenshotCapture;

public class SignUpTest extends TestBase{

	
	@When("User enters {string} credentails and clicks on Signup button")
	public void user_enters_credentails_and_clicks_on_signup_button(String strUserType) throws Throwable {
		String strUserName="";
		String strPassword="";
		
		Thread.sleep(100);
		if(strUserType.equalsIgnoreCase("Existing"))
		{
			strUserName= readConfigParam("Username");
			strPassword= readConfigParam("Password");
		}
		else if(strUserType.equalsIgnoreCase("New"))
				{
			Random rand = new Random();
			int rand_int1 = rand.nextInt(1000);
			strUserName="NP"+String.valueOf(rand_int1);
			strPassword=strUserName;
			}
				
		else
		{
			strUserName= "testinvalid";
				}
		try
		{
			String strReadValue = getParameterValueAgain("Obj_Link_Signup", "OR.properties");
			WebElement element=WaitForObjectExist(strReadValue);
			element.click();
			Thread.sleep(1000);
			strReadValue = getParameterValueAgain("Obj_Txbx_Username", "OR.properties");
			element=WaitForObjectExist(strReadValue);
			element.sendKeys(strUserName);
			Thread.sleep(1000);
			strReadValue = getParameterValueAgain("Obj_Txbx_Password", "OR.properties");
			element=WaitForObjectExist(strReadValue);
			element.sendKeys(strPassword);
			strReadValue = getParameterValueAgain("Obj_Btn_Signup", "OR.properties");
			element=WaitForObjectExist(strReadValue); element.click();
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert(); 
			strAlertMessage = alert.getText();
			System.out.println("Alert message: "+ strAlertMessage); 
			Thread.sleep(2000); 
			alert.accept();
			  
			ScreenshotCapture.captureScreenshot(strAlertMessage);
			 
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		}
	@Then("Verify message after signed up process for {string} sign-up scenario")
	public void verify_message_after_signed_up_process_for_sign_up_scenario(String strExpectedMessage) throws Exception {
				
		Thread.sleep(1000);
	switch (strExpectedMessage)
	{
		case "Successful":
			strExpectedMessage = "Sign up successful.";
			break;
		case "Existing User":
			strExpectedMessage ="This user already exist.";
			break;
		case "Field Validations":
			strExpectedMessage = "Please fill out Username and Password.";
			break;
			}
		
		Assert.assertEquals(strExpectedMessage, strAlertMessage);	  
		
	}
}
