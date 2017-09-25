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
import com.pageObjects.GalleryPage;
public class GalleryPageTest extends TestBaseSetup
{
	public WebDriver driver;
	private GalleryPage obj;
	public Properties prop;

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException 
	{
		prop= new Properties();
		File Utility= new File("Resources/Utility.properties");
		prop.load(new FileInputStream(Utility));
		driver=getDriver();
	}
	/*@Test(priority=0, description="Test Case-1:View Gallery page Image pop up")
	public void viewImagePopup() throws InterruptedException
	{
		obj = new GalleryPage(driver);
		obj.clickGalleryPageImage();
	}*/
	//------------------------Upload Section---------------------//
	@Test(priority=1, description="Test Case-2:click on facebook share button")
	public void clickonFBSharebtn() throws InterruptedException 
	{
		obj = new GalleryPage(driver);
		obj.clickGalleryPageImage();
		obj.clickFBshareBtn();
	}
	@Test(priority=2, description="Test Case-2:facebook Login")
	public void facebookLogin() throws InterruptedException 
	{
		obj = new GalleryPage(driver);
		obj.fbLogin(prop.getProperty("email"),prop.getProperty("pawd"));
	}
	@Test(priority=3, description="Test Case-2:facebook Share")
	public void facebookShare() throws InterruptedException 
	{
		obj = new GalleryPage(driver);
		obj.fbShare();
	}

}
