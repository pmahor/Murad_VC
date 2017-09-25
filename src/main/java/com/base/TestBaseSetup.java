package com.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseSetup 
{
	public WebDriver driver;
	static String driverPath = "Resources/";

	public WebDriver getDriver() 
	{
		return driver;
	}

	private void setDriver(String browserType, String appURL) 
	{
		switch (browserType) 
		{
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "ie":
			driver = initInternetExplorerDriver(appURL);
			break;
		case "safari":
			driver = initSafariDriver(appURL);
			break;

		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}
	private static WebDriver initChromeDriver(String appURL) 
	{
		System.out.println("Launching google chrome...");
		System.setProperty("webdriver.chrome.driver", driverPath
				+ "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	private static WebDriver initFirefoxDriver(String appURL) 
	{
		System.out.println("Launching Firefox browser...");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	private static WebDriver initInternetExplorerDriver(String appURL) 
	{
		System.out.println("Launching Internet Explorer...");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		System.setProperty("webdriver.ie.driver", driverPath
				+ "IEDriverServer.exe");
		caps.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		caps.setCapability("nativeEvents", false);
		WebDriver driver = new InternetExplorerDriver(caps);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	private static WebDriver initSafariDriver(String appURL) 
	{
		System.out.println("Launching Safari browser...");
		WebDriver driver = new SafariDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) 
	{
		try{
			setDriver(browserType, appURL);
		} 
		catch (Exception e) 
		{
			System.out.println("Error....." + e.getStackTrace());
		}	

	}
	@AfterClass
	public void tearDown() 
	{
		//driver.quit();
	}

}
