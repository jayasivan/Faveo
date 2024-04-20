package faveo.com;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Forgotpassword {
	WebDriver driver;
	@Test(priority = 1)
	public void emptyemail() throws InterruptedException {
	WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://jaya.localhost/test/public/home");
	driver.findElement(By.id("details-button")).click();
	driver.findElement(By.id("proceed-link")).click();
	Thread.sleep(1000);
	WebElement Login = driver.findElement(By.id("client_login"));
	Login.click();
	Thread.sleep(900);
	WebElement forgot = driver.findElement(By.xpath("//a[@id='default-forgot-password']"));
	forgot.click();
	driver.findElement(By.xpath("//button[text()='Send']")).click();
	Assert.assertTrue(driver.getPageSource().contains("is required"));
	if(driver.getPageSource().contains("is required")) {
	    System.out.println("Proof: The page source contains the text 'is required'.");
	} else {
	    System.out.println("Proof: The page source does not contain the text 'is required'.");
	}
	Thread.sleep(1500);
	}
	@Test(priority = 2)
    public void testForgotPasswordWithInvalidEmail() throws InterruptedException {
		
			WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://jaya.localhost/test/public/home");
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();
			Thread.sleep(1000);
			WebElement Login = driver.findElement(By.id("client_login"));
			Login.click();
			Thread.sleep(900);
			WebElement forgot = driver.findElement(By.xpath("//a[@id='default-forgot-password']"));
			forgot.click();
			WebElement fillemail = driver.findElement (By.xpath("//input[@name='email_address']"));
			fillemail.sendKeys("invalidemail@.in");
			driver.findElement(By.xpath("//button[text()='Send']")).click();
			Assert.assertTrue(driver.getPageSource().contains("Please type a valid email address"));
			if(driver.getPageSource().contains("Please type")) {
			    System.out.println("Proof: The page source contains the text Please type");
			} else {
			    System.out.println("Proof: The page source does not contain the text 'Please type'.");
			}

			}
	
	 @Test(priority = 3)
	    public void testForgotPasswordWithEmail() throws InterruptedException, IOException {
		 WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://jaya.localhost/test/public/home");
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();
			Thread.sleep(1000);
			WebElement Login = driver.findElement(By.id("client_login"));
			Login.click();
			Thread.sleep(900);
			WebElement forgot = driver.findElement(By.xpath("//a[@id='default-forgot-password']"));
			forgot.click();
			WebElement fillemail = driver.findElement (By.xpath("//input[@name='email_address']"));
			fillemail.sendKeys("invalidemail@m.in");
			
			driver.findElement(By.xpath("//button[text()='Send']")).click();
			Thread.sleep(1000);
			TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
			File src=tk.getScreenshotAs(OutputType.FILE);
			File des=new File("\\.eclipse\\screenshot\\register.png");
			FileUtils.copyFile(src, des);
	 }
	 
}
