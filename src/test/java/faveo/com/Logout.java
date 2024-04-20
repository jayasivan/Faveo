package faveo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Logout {
	WebDriver driver;
	@Test(priority = 1)
	
	public void logout() throws InterruptedException {
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
	passoword.sendKeys("Demopass@1");
	Thread.sleep(1000);
	WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
	Submit.click();
	Thread.sleep(2500);
	driver.findElement(By.xpath("//a[@id='navbarDropdownProfile']")).click();
	driver.findElement(By.xpath("//button[text()='Logout']")).click();
	 
	Thread.sleep(2500);
  
   if (driver.getCurrentUrl().contains("home")) {
	   System.out.println("Logged out successfully");
	
}
   if (driver.getPageSource().contains("login")) {
	   System.out.println("Please login again");
} else {
	System.out.println("Please Check again");
}
   //driver.close();
}
}
