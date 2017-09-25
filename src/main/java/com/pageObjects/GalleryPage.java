package com.pageObjects;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GalleryPage 
{
	public WebDriver driver;
	public GalleryPage gp;
	public String P_window;
	//----------------------Locators-----------------------//
	private By iframe1 = By.id("sa_s22_iframe_load");  
	private By image1_ele = By.xpath(".//*[@id='sa_s22_template_photo_container']/div/div[1]/img");
	private By sharesuccessele= By.id("sa_s22_successMessage");
	private By lightboxele= By.id("//a[@class='fancybox-item fancybox-close firepath-matching-node']");
	//FB Share
	private By fbshare_Btn_ele = By.xpath("//div[@id='slider_1363873']/ul/li/div[4]/div/div[1]/a");
	private By userID = By.id("email");
	private By password = By.id("pass");
	private By loginBtn = By.xpath(".//*[@id='u_0_2']");
	private By postBtnele= By.xpath("//button[contains(.,'Post to Facebook')]");
	//Twitter Share
	private By tweetsharebtn_ele = By.xpath("//*[@id='slider_1264498']/ul/li/div[4]/div/div[2]/a");
	private By emailTbox= By.xpath("//*[@id='username_or_email']");
	private By passwdTbox= By.xpath("//input[@id='password']");
	private By signinbtn= By.xpath(".//*[@id='allow']");
	//Pinterest Share
	private By pinsharebtn_ele= By.xpath("//*[@id='slider_1264498']/ul/li/div[4]/div/div[3]/a");
	private By userEmailTboxele= By.id("userEmail");
	private By userpasswdTboxele= By.id("userPassword");
	private By signupbutn_ele= By.xpath("//button[@class='Button Module btn hasText medium primary large userRegisterButton continueButton rounded']");
	private By p_savebtn_ele= By.xpath("//button[@class='Button Module btn hasText isBrioFlat pinitLocalization primary primaryOnHover repinSmall repinBtn rounded']");
	private By u_nameTboxele= By.id("sa_s22_user_name");
	private By u_emailTboxele= By.id("sa_s22_user_email");
	private By continuetoshare_Btnele= By.id("sa_s22_pinterest_submit");
	//Product Link Test
	private By productLink1= By.xpath("//*[@id='slider_1264498']/ul/li/div[3]/a");
	private By buynowBtnele= By.xpath("//div[@id='slider_1264498']/ul/li/div[6]/a");
	private By nextSliderBtn= By.xpath("//div[@id='myCarousel']/a[2]");
	private By previousSliderBtn= By.xpath("//div[@id='myCarousel']/a[1]");
	private By closeSliderImageBtn= By.xpath("//*[@id='sa_s22_slider_close_icon']/a/img");
	//---------------------------------------------------//
	public GalleryPage(WebDriver driver) 
	{
		this.driver=driver;
		//driver.manage().deleteAllCookies();
	}
	public void clickGalleryPageImage() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(lightboxele).click();
		Thread.sleep(10000);
		WebElement image1 = driver.findElement(image1_ele);
		image1.click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(iframe1));
		Thread.sleep(3000);
	}
	public void clickFBshareBtn() 
	{
		try{
			WebElement fbsharebtn = driver.findElement(fbshare_Btn_ele);	
			fbsharebtn.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void fbLogin(String email, String pawd) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	public void fbShare() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.numberOfWindowsToBe(1));

		Set<String> winHandles = driver.getWindowHandles();
		driver.switchTo().window(new ArrayList<String>(winHandles).get(0));		   
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(iframe1)));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".FB_UI_Dialog")));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement PostBtn= driver.findElement(postBtnele);
		Thread.sleep(6000);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",PostBtn);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		Thread.sleep(6000);
		driver.switchTo().frame(driver.findElement(iframe1));
		Thread.sleep(6000);
		WebElement fb_sharesuccess= driver.findElement(sharesuccessele);
		String fbSharemsg= fb_sharesuccess.getText();
		System.out.println("FB Share message:"+fbSharemsg);
		Thread.sleep(3000);
		fb_sharesuccess.click();
		Thread.sleep(3000);
	}
	// Twitter Share
	public void click_tweetShareBtn() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(tweetsharebtn_ele).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	public void twitterLogin(String username, String t_passwd) throws InterruptedException
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
		jst.executeScript("arguments[1].value = arguments[0]; ", username, emailTextbox );
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
		Thread.sleep(2000);
	}
	public void twitterShare() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(iframe1));
		WebElement tw_sharesuccess= driver.findElement(sharesuccessele);
		String tweetSharemsg= tw_sharesuccess.getText();
		System.out.println("Twitter Share message:"+tweetSharemsg);
		Thread.sleep(3000);
		tw_sharesuccess.click();
		Thread.sleep(3000);
	}
	// Pinterest Share
	public void click_pinShareBtn() throws InterruptedException
	{
		Thread.sleep(3000);
		//driver.manage().deleteAllCookies();
		driver.findElement(pinsharebtn_ele).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	public void pinterestLogin(String userEmail, String userPaswd) throws InterruptedException
	{
		Thread.sleep(6000);
		P_window=driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
		}
		try{
			Thread.sleep(5000);
			WebElement userEmailTbox= driver.findElement(userEmailTboxele);
			JavascriptExecutor jst_p1 = (JavascriptExecutor) driver;
			jst_p1.executeScript("arguments[1].value = arguments[0]; ", userEmail, userEmailTbox );
			Thread.sleep(3000);
			WebElement userPaswdTbox= driver.findElement(userpasswdTboxele);
			JavascriptExecutor jst_p2 = (JavascriptExecutor) driver;
			jst_p2.executeScript("arguments[1].value = arguments[0]; ", userPaswd, userPaswdTbox );
			Thread.sleep(3000);
			WebElement signupbtn= driver.findElement(signupbutn_ele);
			signupbtn.click();
			Thread.sleep(6000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void pinShare(String u_name,String u_email) throws InterruptedException
	{
		Thread.sleep(6000);
		WebElement p_savebtn= driver.findElement(p_savebtn_ele);
		p_savebtn.click();
		Thread.sleep(10000);
		driver.switchTo().window(P_window);
		Thread.sleep(6000);
		driver.switchTo().defaultContent();
		Thread.sleep(6000);
		driver.switchTo().frame(driver.findElement(iframe1));
		Thread.sleep(3000);
		WebElement u_nameTbox= driver.findElement(u_nameTboxele);
		JavascriptExecutor jst_p3 = (JavascriptExecutor) driver;
		jst_p3.executeScript("arguments[1].value = arguments[0]; ", u_name, u_nameTbox );
		Thread.sleep(1000);
		WebElement u_emailTbox= driver.findElement(u_emailTboxele);
		JavascriptExecutor jst_p4 = (JavascriptExecutor) driver;
		jst_p4.executeScript("arguments[1].value = arguments[0]; ", u_email, u_emailTbox );
		Thread.sleep(2000);
		WebElement continuetoshare= driver.findElement(continuetoshare_Btnele);
		continuetoshare.click();
		Thread.sleep(5000);
		WebElement p_sharesuccess= driver.findElement(sharesuccessele);
		String pinSharemsg= p_sharesuccess.getText();
		System.out.println("Pinterest Share message:"+pinSharemsg);
		Thread.sleep(3000);
		p_sharesuccess.click();
		Thread.sleep(3000);
	}
	//Product Link test from homepage slider
	public void productLink() throws InterruptedException
	{
		/*Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(iframe1));*/
		Thread.sleep(3000);
		WebElement productLink = driver.findElement(productLink1);
		productLink.click();
		String ProductTitle= productLink.getText();
		String actualProductTitle= ProductTitle.toLowerCase();
		String parentWin= driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
		}
		String expectedProductTitle= driver.getTitle();
		String lineseparator=System.getProperty("line.separator");
		System.out.println("actualProductTitle:"+actualProductTitle+lineseparator+"expectedProductTitle:"+expectedProductTitle);
		if(actualProductTitle.equals(expectedProductTitle))
		{
			System.out.println("PASS:Successfully redirected to the given product Link");			
		}
		else
		{
			System.out.println("Fail:Redirected to the wrong Product Link");
		}
		Thread.sleep(3000);
		driver.switchTo().window(parentWin);
		Thread.sleep(3000);
		driver.switchTo().defaultContent();	
		Thread.sleep(3000);
	}
	//Buy now button Link test
	public void buyNowButton() throws InterruptedException
	{
		Thread.sleep(4000);
		driver.switchTo().frame(driver.findElement(iframe1));
		Thread.sleep(6000);
		WebElement buynowbuttonLink = driver.findElement(buynowBtnele);
		buynowbuttonLink.click();
		WebElement productLink = driver.findElement(productLink1);
		String ProductTitle= productLink.getText();
		String actualProduct_Title= ProductTitle.toLowerCase();
		String parent_Win= driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
		}
		String expectedProduct_Title= driver.getTitle();
		if(actualProduct_Title.equals(expectedProduct_Title))
		{
			System.out.println("PASS:BuyNow Btn-Successfully redirected to the given product Link");			
		}
		else
		{
			System.out.println("Fail:BuyNow Btn-Redirected to the wrong Product Link");
		}
		Thread.sleep(3000);
		driver.switchTo().window(parent_Win);
		Thread.sleep(3000);
	}
	//Previous Slider Image button 
	public void previousSliderImagebutton() throws InterruptedException
	{
		Thread.sleep(6000);
		driver.switchTo().defaultContent();	
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(iframe1));
		Thread.sleep(3000);
		WebElement previousBtn= driver.findElement(previousSliderBtn);
		previousBtn.click();
		Thread.sleep(3000);
	}
	//Next Slider Image button 
	public void nextSliderImagebutton() throws InterruptedException
	{
		Thread.sleep(6000);
		WebElement nextBtn= driver.findElement(nextSliderBtn);
		nextBtn.click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
	}
	//Close Slider Image button 
	public void closeSliderImagebutton() throws InterruptedException
	{
		Thread.sleep(4000);
		WebElement closeBtn= driver.findElement(closeSliderImageBtn);
		closeBtn.click();
		Thread.sleep(3000);
	}

}
