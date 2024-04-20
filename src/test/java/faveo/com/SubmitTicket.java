package faveo.com;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitTicket {
	WebDriver driver;

	//Check for error message if mandatory fields are not filled
	//Steps
	//1.Access the URL
	//2.Login as client
	//3.Click 'Submit Ticket' link
	//4.Without enter all the mandatory fields just click submit button
	
	@Test(priority = 1)
	
	public void emptyfieldvalidation() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		
		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Login.click();
		
		Thread.sleep(2000);
		
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("jaisivamech1@outlook.com");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("Demopass@1");
		
		Thread.sleep(2500);
		
		WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		Submit.click();
		
		Thread.sleep(3000);
		
		WebElement pagesource = driver.findElement(By.cssSelector("#navbarDropdownProfile"));
		Assert.assertTrue(pagesource.getText().contains("My Profile"));
		if(driver.getPageSource().contains("My Profile")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		} else {
			System.out.println("Proof: My profile is not present please login again.");
		}

		Thread.sleep(2500);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		WebElement pagedown=driver.findElement(By.xpath("//button[@id='client_form_submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);",pagedown);
		
		Thread.sleep(2000);

		WebElement submitButton = driver.findElement(By.id("client_form_submit"));
		submitButton.click();
		Thread.sleep(2000);
		WebElement subjectq=driver.findElement(By.xpath("(//span[@class='error-block is-danger'])[2]"));
		String fldrequired =subjectq.getText();
		System.out.println("Subject is required:" +fldrequired);
		WebElement priority1 = driver.findElement(By.xpath("(//span[@class='error-block is-danger'])[3]"));
		String priori=priority1.getText();
		System.out.println("Priority is required:" +priori);
		WebElement Htopic=driver.findElement(By.xpath("(//span[@class='error-block is-danger'])[4]"));
		String help=Htopic.getText();
		System.out.println("Helptopic is required:" +help);
		WebElement destion=driver.findElement(By.xpath("(//span[@class='error-block is-danger'])[6]"));
		String description=destion.getText();
		System.out.println("Description is required:" +description);
		Thread.sleep(10000);
	}
	//Create a ticket of type(Feature Request) with user login
	//Steps 
	//1.Access the URL
	//2.Login as client
	//3.Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
	//4.Enter all the mandatory fields, Choose Ticket type as 'Feature request)and click submit button



	@Test(priority = 2)

	public void FeatureRequest() throws InterruptedException, AWTException, IOException {



		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		
		Thread.sleep(1000);
		
		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Login.click();
		
		Thread.sleep(1000);
		
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("jaisivamech1@outlook.com");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("Demopass@1");
		
		Thread.sleep(3000);



		WebElement Submit = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		Submit.click();
		
		Thread.sleep(3000);
		
		WebElement pagesource = driver.findElement(By.cssSelector("#navbarDropdownProfile"));
		Assert.assertTrue(pagesource.getText().contains("My Profile"));

		if(driver.getPageSource().contains("My Profile")) {
			Thread.sleep(2000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		} else {
			System.out.println("Proof: My profile is not present please login again.");
		}

		// Check if the 'Ticket summary' dropdown is displayed
		WebElement ticketSummaryDropdown = driver.findElement(By.id("section-summary"));
		// Verify that the 'Ticket summary' dropdown is displayed
		Assert.assertTrue(ticketSummaryDropdown.isDisplayed(), "Ticket summary dropdown is not displayed.");
		
		Thread.sleep(2500);
		
		WebElement subject=driver.findElement(By.id("subject"));
		subject.click();

		WebElement subject1=driver.findElement(By.xpath("//input[@name='subject']"));
		Thread.sleep(2500);
		
		subject1.sendKeys("ticket is creating by user");
		
		Thread.sleep(1500);
		
		WebElement priority = driver.findElement(By.id("priority_id"));
		priority.click();
		
		Thread.sleep(1000);
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		WebElement Helptopic = driver.findElement(By.xpath("//*[@id=\"help_topic_id\"]"));
		Helptopic.click();
		
		Thread.sleep(500);
		
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(1000);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		WebElement pagedown=driver.findElement(By.xpath("//button[@id='client_form_submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);",pagedown);
		WebElement type = driver.findElement(By.xpath("//*[@id=\"type_id\"]"));
		type.click();
		
		Thread.sleep(1000);
		
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(2000);
		
		WebElement iframeElement = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframeElement);
		WebElement des = driver.findElement(By.cssSelector("#tinymce > p"));
		des.sendKeys("the rich text editor is within an iframe");
		
		Thread.sleep(1000);
		
		driver.switchTo().defaultContent();
		WebElement submitButton = driver.findElement(By.id("client_form_submit"));
		submitButton.click();
		
		Thread.sleep(2000);
		
		TakesScreenshot tk=(TakesScreenshot)driver; //Taking screenshot for valid user registration
		File src=tk.getScreenshotAs(OutputType.FILE);
		File des1=new File("\\.eclipse\\screenshot\\valid1.png");
		FileUtils.copyFile(src, des1);
		Thread.sleep(3000);
		driver.close();
	}



	//Positive scenarios for Submit Ticket- Ticket summary with user login
	//Check for view open tickets
	//Steps
	//1.Login as client
	//2.Click submit ticket
	//3.Verify the open tickets in Ticket summary panel
	//4.Click on the open ticket
	
	
	@Test(priority = 3)
	public void openticketsummary() throws InterruptedException {

		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		
		Thread.sleep(1000);
		
		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Login.click();
		
		Thread.sleep(1000);
		
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("jaisivamech1@outlook.com");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("Demopass@1");
		
		Thread.sleep(2500);
		
		WebElement login = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		login.click();
		
		Thread.sleep(3000);
		
		WebElement pagesource = driver.findElement(By.cssSelector("#navbarDropdownProfile"));
		Assert.assertTrue(pagesource.getText().contains("My Profile"));
		if(driver.getPageSource().contains("My Profile")) {
			
			Thread.sleep(3000);
			
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		} else {
			System.out.println("Proof: My profile is not present please login again.");
		}

		Thread.sleep(2000);
		
		WebElement opentickets = driver.findElement(By.xpath("(//a[@class='list-group-item list-group-item-action'])[1]"));
		opentickets.click();
		
		Thread.sleep(2000);

		JavascriptExecutor js=(JavascriptExecutor)driver;		
		WebElement down=driver.findElement(By.cssSelector("#colophon > div > div > div.site-info.col-md-6 > p"));
		js.executeScript("arguments[0].scrollIntoView(true);",down);
		WebElement records = driver.findElement(By.cssSelector("#my_tic > div > div.VueTables.VueTables--server > div.VuePagination.row.col-md-12 > nav > p"));
		String counts=records.getText();

		if (counts.contains("records")) {
			System.out.println("Number of Open tickets:" +counts );
		}
	}
	
	//Check for view closed tickets
	//1.Login as client
	//2.Click submit ticket
	//3.Verify the closed tickets in Ticket summary panel
	//4.Click on the closed ticket
	
	
	@Test(priority = 4)
	public void closedticket() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/faveo1/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		
		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Login.click();
		
		Thread.sleep(1000);
		
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("jaisivamech1@outlook.com");
		WebElement passoword = driver.findElement(By.name("password"));
		passoword.sendKeys("Demopass@1");
		
		Thread.sleep(2500);
		
		WebElement login = driver.findElement(By.xpath("//button[@id='default-login-button']"));
		login.click();
		
		Thread.sleep(3000);
		
		WebElement pagesource = driver.findElement(By.cssSelector("#navbarDropdownProfile"));
		Assert.assertTrue(pagesource.getText().contains("My Profile"));
		if(driver.getPageSource().contains("My Profile")) {
			
			Thread.sleep(3000);
			
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		} else {
			System.out.println("Proof: My profile is not present please login again.");
		}

		Thread.sleep(2000);
		
		WebElement closedtickets = driver.findElement(By.xpath("//*[@id=\"ticket_list_1\"]"));
		closedtickets.click();
		
		Thread.sleep(2000);

		JavascriptExecutor js=(JavascriptExecutor)driver;		
		WebElement down=driver.findElement(By.cssSelector("#colophon > div > div > div.site-info.col-md-6 > p"));
		js.executeScript("arguments[0].scrollIntoView(true);",down);
		WebElement records = driver.findElement(By.cssSelector("#my_tic > div > div.VueTables.VueTables--server > div.VuePagination.row.col-md-12 > nav > p"));
		String counts=records.getText();

		if (counts.contains("records")) {
			System.out.println("Number of Closed tickets:" +counts );
		}

else {
	System.out.println("check the script");
}
	}
}




