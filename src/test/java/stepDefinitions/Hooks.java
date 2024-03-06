package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class Hooks extends TestBase {


	// create driver instance based on the driver type mentioned in Configuration.properties file
	public static void createDriver()
	{
		String strDriverPath = readConfigParam("Driverpath");
		String strDriverType = readConfigParam("Drivertype");

		switch(strDriverType) {
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.firefox.driver", strDriverPath +"\\GeckoDriver.exe");
			driver=new FirefoxDriver();
			break;
		}
		objWaitDriver=new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	//This code gets called before during each scenario execution
	@Before
	public void init() {
		createDriver();
		driver.manage().window().maximize();
		String str= readConfigParam("Implicitlywait");
		longImplicitWait = Long.parseLong(str);
		String strUrl=readConfigParam("Url");
		driver.get(strUrl);
	}

	//This code gets called at the end of the each scenario execution

	@After 
	public static void tearDown() { 
		driver.quit(); 
	}

	//This code gets called at the end of the all scenario execution

	@AfterAll 
	public static void closure() throws Exception {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T"); 
		
	}
}
	
