package stepDefinitions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ScreenshotCapture;

public class TestBase {
        private static String strPropertyFilePath= "src//test//resources//Properties//Configuation.properties";
		private static Properties properties;
		public static int intStepCounter = 1; 
		
		public static long longImplicitWait;
		public  static WebDriver driver=null;
		public static WebDriverWait objWaitDriver=null;
		public static String strAlertMessage;
		
		String strMessage;
		
			public TestBase(){
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(strPropertyFilePath));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + strPropertyFilePath);
			}		
	
	
		}
	
		public static String readConfigParam(String strParameterName){
			String strConfigParam = properties.getProperty(strParameterName);
			if(strConfigParam!= null) return strConfigParam;
			else throw new RuntimeException("No Parameter/Value is specified in the Configuration.properties file for the Key: "+strConfigParam);		
		}
	
		
		public String getParameterValueAgain(String strParamterName, String strFileName) throws IOException
		{
			InputStream input=null;
			Properties objProp=new Properties();
			input=getClass().getClassLoader().getResourceAsStream(strFileName);
			objProp.load(input);
			String strParameterValue= objProp.getProperty(strParamterName);
			return strParameterValue;
		}
		public static void setStepCounter()
		{
			try {
				intStepCounter ++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static WebElement WaitForObjectExist(String strXpath)
		{
			return objWaitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strXpath)));
		}
		public void appLogin()
		{
		String strUserName= readConfigParam("Username");
		String strPassword= readConfigParam("Password");
	
		try
		{
			
			WebElement element=driver.findElement(By.partialLinkText("Log"));
			element.click();
			String strReadValue = getParameterValueAgain("Obj_Txbx_Login", "OR.properties");
			element=WaitForObjectExist(strReadValue);
			element.sendKeys(strUserName);
			Thread.sleep(2000);
			strReadValue = getParameterValueAgain("Obj_Txbx_PassLogin", "OR.properties");
			element=WaitForObjectExist(strReadValue);
			element.sendKeys(strPassword);
			Thread.sleep(2000);
			strReadValue = getParameterValueAgain("Obj_Btn_Login", "OR.properties");
			element=WaitForObjectExist(strReadValue); element.click();
			  
			ScreenshotCapture.captureScreenshot(strAlertMessage);
			 
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		}
}
