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

public class Login {
	WebDriver driver;
	//@Test(priority = 1)
	
	public void emptyfield() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		WebElement Login = driver.findElement(By.id("client_login"));
		Login.click();
		Thread.sleep(1000);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("");
		Thread.sleep(1000);
		WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		Submit.click();
		WebElement unamerequired = driver.findElement(By.xpath("//*[@id=\"username\"]/div[2]/div/div"));
		String fldrequired =unamerequired.getText();
		System.out.println("Username is required:" +fldrequired);
		WebElement passrequired = driver.findElement(By.xpath("//*[@id=\"Password\"]/div[2]/div/div[2]"));
		String pssrequired =passrequired.getText();
		System.out.println("Password is required:"+pssrequired);
		Thread.sleep(2000);
		driver.close();
		
	}
	//@Test(priority = 2)
	public void invalidusernameandvalidpass() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		WebElement Login = driver.findElement(By.id("client_login"));
		Login.click();
		Thread.sleep(1000);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("demo");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("Demopass@1");
		Thread.sleep(1000);
		WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		Submit.click();
		Thread.sleep(800);
		TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\register.png");
		FileUtils.copyFile(src, des);
		driver.close();
	}
	//@Test(priority = 3)
	public void validusernameandInvalidpass() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		WebElement Login = driver.findElement(By.id("client_login"));
		Login.click();
		Thread.sleep(1000);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("jaisivamech@outlook.com");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("demopass123");
		Thread.sleep(1000);
		WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		Submit.click();
		Thread.sleep(800);
		TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\invalid.png");
		FileUtils.copyFile(src, des);
		driver.close();
	}
	@Test(priority = 4)
	public void valicredentials() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		WebElement Login = driver.findElement(By.id("client_login"));
		Login.click();
		Thread.sleep(1000);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("jaisivamech1@outlook.com");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("Demopass@1");
		Thread.sleep(1000);
		WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		Submit.click();
		Thread.sleep(3000);
		
		WebElement pagesource = driver.findElement(By.cssSelector("#navbarDropdownProfile"));
		Assert.assertTrue(pagesource.getText().contains("My Profile"));
		
		System.out.println("My profile found");
			
		
		Thread.sleep(3500);
		TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\valid.png");
		FileUtils.copyFile(src, des);
		Thread.sleep(2500);
			}
	
}
