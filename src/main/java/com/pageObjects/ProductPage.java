package com.pageObjects;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage 
{
	public WebDriver driver;
	public ProductPage pp;
	public String P_window;
	//----------------------Locators-----------------------//
	private By parentiframe= By.id("sa_s22_main_iframe");
	private By iframe1 = By.id("sa_s22_iframe_load");  
	private By image1_ele = By.xpath("//img[contains(@alt,'photo1366082')]");
	//fbShare service
	private By fbsharebtnele=By.xpath("//div[@id='slider_1366082']/ul/li/div[4]/div/div[1]/a");
	private By userID = By.id("email");
	private By password = By.id("pass");
	private By loginBtn = By.xpath(".//*[@id='u_0_2']");
	private By postBtnele= By.xpath("//button[contains(.,'Post to Facebook')]");
	//Twitter Share
	private By tweetsharebtn_ele = By.xpath("//div[@id='slider_1366082']/ul/li/div[4]/div/div[2]/a");
	private By emailTbox= By.id("username_or_email");
	private By passwdTbox= By.id("password");
	private By signinbtn= By.id("allow");
	//Pinshare
	private By pinsharebtn_ele=By.xpath(".//*[@id='slider_1366082']/ul/li/div[4]/div/div[3]/a");
	public ProductPage(WebDriver driver) 
	{
		this.driver=driver;
	}

	public void viewProductimage(String productURL) throws InterruptedException
	{
		driver.navigate().to(productURL);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(6000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(80, 0)");
		Thread.sleep(6000);
		driver.switchTo().frame(driver.findElement(parentiframe));
		Thread.sleep(2000);
		WebElement image1= driver.findElement(image1_ele);
		image1.click();
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	public void clickfbSharebtn() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.switchTo().frame("sa_s22_iframe_load");
		driver.findElement(fbsharebtnele).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void fblogin(String email, String pawd) throws InterruptedException
	{
		Thread.sleep(3000);
		Set<String> winHandles = driver.getWindowHandles();
		for(String handle : winHandles){
			driver.switchTo().window(handle);
		}		  
		WebElement Emailtextbox= driver.findElement(userID);
		JavascriptExecutor jst1 = (JavascriptExecutor) driver;
		jst1.executeScript("arguments[1].value = arguments[0]; ", email, Emailtextbox );
		Thread.sleep(1000);  
		WebElement passwdtextbox=driver.findElement(password);
		JavascriptExecutor jst2 = (JavascriptExecutor) driver;
		jst2.executeScript("arguments[1].value = arguments[0]; ", pawd, passwdtextbox );
		Thread.sleep(1000);    
		//click on Login button
		driver.findElement(loginBtn).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	public void fbshare() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.numberOfWindowsToBe(1));

		Set<String> winHandles = driver.getWindowHandles();
		driver.switchTo().window(new ArrayList<String>(winHandles).get(0));		   
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(iframe1)));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".FB_UI_Dialog")));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement PostBtn= driver.findElement(postBtnele);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",PostBtn);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		System.out.println("Your facebook share has been sent successfully.");
		Thread.sleep(6000);
	}
	public void clicktweetbutton() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(iframe1));
		driver.findElement(tweetsharebtn_ele).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
	}
	public void tweetshare(String t_username, String t_passwd) throws InterruptedException
	{
		Thread.sleep(3000);
		String parentwindow=driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
		} 
		Thread.sleep(3000);
		WebElement emailTextbox= driver.findElement(emailTbox);
		JavascriptExecutor jst = (JavascriptExecutor) driver;
		jst.executeScript("arguments[1].value = arguments[0]; ", t_username, emailTextbox );
		Thread.sleep(1000);
		WebElement passwdTextbox= driver.findElement(passwdTbox);
		JavascriptExecutor jst_t1 = (JavascriptExecutor) driver;
		jst_t1.executeScript("arguments[1].value = arguments[0]; ", t_passwd, passwdTextbox );
		Thread.sleep(1000);
		driver.findElement(signinbtn).click();
		Thread.sleep(1000);
		driver.switchTo().window(parentwindow);
		// to switch back in iframe
		driver.switchTo().defaultContent(); 
		System.out.println("Twitter share has been sent successfully.");
		Thread.sleep(2000);
	}
	// Pinterest Share
	public void click_pinShareBtn() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(iframe1));
		driver.findElement(pinsharebtn_ele).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
