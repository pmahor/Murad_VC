package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBaseSetup;
import com.pageObjects.ProductPage;

public class ProductPageTest extends TestBaseSetup
{
	public WebDriver driver;
	private ProductPage obj1;
	public Properties prop;

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException 
	{
		prop= new Properties();
		File Utility= new File("Resources/Utility.properties");
		prop.load(new FileInputStream(Utility));
		driver=getDriver();
	}
	@Test(priority=0, description="Test Case-1:View Product page Image pop up")
	public void viewImagePopup_p() throws InterruptedException
	{
		obj1 = new ProductPage(driver);
		obj1.viewProductimage(prop.getProperty("productURL"));
	}
	@Test(priority=1, description="Test Case-2:click on facebook share button")
	public void clickonFBSharebtn() throws InterruptedException 
	{
		obj1 = new ProductPage(driver);
		obj1.clickfbSharebtn();
	}
	@Test(priority=2, description="Test Case-3:facebook Login")
	public void facebooklogin() throws InterruptedException 
	{
		obj1 = new ProductPage(driver);
		obj1.fblogin(prop.getProperty("email"),prop.getProperty("pawd"));
	}
	@Test(priority=3, description="Test Case-4:facebook Share")
	public void facebookshare() throws InterruptedException 
	{
		obj1 = new ProductPage(driver);
		obj1.fbshare();
	}
	@Test(priority=4, description="Test Case-5:click on twitter share button")
	public void clickonTweetbtn() throws InterruptedException
	{
		obj1 = new ProductPage(driver);
		obj1.clicktweetbutton();
	}
	@Test(priority=5, description="Test Case-5:Twitter Share")
	public void twittershare() throws InterruptedException 
	{
		obj1 = new ProductPage(driver);
		obj1.tweetshare(prop.getProperty("t_username"), prop.getProperty("t_passwd"));
	}

}
