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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Register {
	WebDriver driver;

	//VALID USER REGISTRATION
	@Test(priority = 1)
	public void Validuserregistration() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launchin the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("client_widget_register")).click();
		Thread.sleep(500);
		driver.findElement(By.name("first_name")).sendKeys("sivan");     //Passing valid firstname
		driver.findElement(By.name("last_name")).sendKeys("jaya");       //Passing valid lastname
		WebElement email = driver.findElement(By.name("email")); //Passing calid email
		email.sendKeys("jaisivamech@outlook.com");
		driver.findElement(By.id("client_form_submit")).click(); //Clicking submit button
		Thread.sleep(800);
		TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\register.png");
		FileUtils.copyFile(src, des);


	}

	//same user registration
	@Test(priority = 2)
	public void Smaeuserregistration() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("client_widget_register")).click();
		Thread.sleep(500);
		driver.findElement(By.name("first_name")).sendKeys("sivan");     //Passing valid firstname
		driver.findElement(By.name("last_name")).sendKeys("jaya");       //Passing valid lastname
		WebElement email=driver.findElement(By.name("email"));
		email.sendKeys("jaisivamech@outlook.com"); //Passing calid email
		driver.findElement(By.id("client_form_submit")).click(); //Clicking submit button
		Thread.sleep(1000);
		TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\samemail.png");
		FileUtils.copyFile(src, des);
		WebElement emailtaken = driver.findElement(By.xpath("//div[@class='error-block is-danger']"));
		String messagelast=emailtaken.getText();
		System.out.println(messagelast);
		Thread.sleep(1000);
		email.clear();
		email.sendKeys("jayasivanm1996@outlook.com");
		driver.findElement(By.id("client_form_submit")).click();
		Thread.sleep(800);
		TakesScreenshot tk1=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src1=tk1.getScreenshotAs(OutputType.FILE);
		File des1=new File("\\.eclipse\\screenshot\\new.png");
		FileUtils.copyFile(src1, des1);


		//driver.close();
	}

	//REGISTER WITH EMPTY FIELD
	@Test(priority = 3)
	public void Emptyfieldregistration() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("client_widget_register")).click();
		Thread.sleep(500);
		driver.findElement(By.name("first_name")).sendKeys("");
		driver.findElement(By.name("last_name")).sendKeys("");
		driver.findElement(By.name("email")).sendKeys("");
		driver.findElement(By.id("client_form_submit")).click();
		Thread.sleep(800);
		TakesScreenshot tk=(TakesScreenshot)driver;
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\empty.png");
		FileUtils.copyFile(src, des);
		Thread.sleep(800);
		driver.quit();
	}
	//Invalid email validation
	@Test(priority = 4)
	public void testInvalidEmailFormat() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("client_widget_register")).click();
		Thread.sleep(500);
		driver.findElement(By.name("first_name")).sendKeys("maven");
		driver.findElement(By.name("last_name")).sendKeys("risk");
		driver.findElement(By.name("email")).sendKeys("maven@bdd");
		driver.findElement(By.id("client_form_submit")).click();
		WebElement validemail=driver.findElement(By.xpath("//span[text()='Please type a valid email address ']"));	
		String validemail1=validemail.getText();
		System.out.println(validemail1);
		if (validemail1.equals(validemail1)) {
			driver.findElement(By.name("email")).clear();
			driver.findElement(By.name("email")).sendKeys("validemai3l@m.in");
			driver.findElement(By.id("client_form_submit")).click();
		} else {
            System.out.println("Valid email added");
		}
		Thread.sleep(800);
		TakesScreenshot tk=(TakesScreenshot)driver;
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\invalidemail.png");
		FileUtils.copyFile(src, des);
		driver.quit();
	}
	//Firstname and lastname field validation
	@Test(priority = 5)
	public void FirstnameLastnamefieldvalidation() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("client_widget_register")).click();
		Thread.sleep(500);
		WebElement firstname=driver.findElement(By.name("first_name"));
		firstname.sendKeys("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");


		WebElement lastname=driver.findElement(By.name("last_name"));
		lastname.sendKeys("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		driver.findElement(By.name("email")).sendKeys("maven@bdd.com");
		driver.findElement(By.id("client_form_submit")).click();

		Thread.sleep(800);
		TakesScreenshot tk=(TakesScreenshot)driver;
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des=new File("\\.eclipse\\screenshot\\firstname.png");
		FileUtils.copyFile(src, des);

		WebElement firstvalidation=driver.findElement(By.xpath("(//div[@class='error-block is-danger'])[1]"));
		String message=firstvalidation.getText();
		System.out.println(message);
		WebElement lasttvalidation=driver.findElement(By.xpath("(//div[@class='error-block is-danger'])[2]"));
		String messagelast=lasttvalidation.getText();
		System.out.println(messagelast);
		
		if (message.equals(message)) {
			firstname.clear();
			firstname.sendKeys("thirtycahrecters");
			driver.findElement(By.id("client_form_submit")).click();

		}
		else {
			System.out.println("Fisrtname length is high");
		}

		
		Thread.sleep(500);
		if (messagelast.equals(messagelast)) {
			lastname.clear();
			lastname.sendKeys("thirychar");
			driver.findElement(By.id("client_form_submit")).click();
			Thread.sleep(800);
			TakesScreenshot tk1=(TakesScreenshot)driver;
			File src1=tk1.getScreenshotAs(OutputType.FILE);
			File des1=new File("\\.eclipse\\screenshot\\firstname.png");
			FileUtils.copyFile(src1, des1);
			
			
		} else {

			System.out.println("Last name added");
		}
       driver.close();

	}
	
}
