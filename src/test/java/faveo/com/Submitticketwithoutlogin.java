package faveo.com;

import java.awt.AWTException;           // Note: change the different email id's for both the classes if we are running the program multiple tiles
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Submitticketwithoutlogin {
	WebDriver driver;
	//Create ticket with unknown user and register new user and submit the ticket
	//1. Access the URL,
	//2. Click submit ticket
	//3. Fill all the mandatory fields click submit button
	//Expected Result: “it should display the alert message like please register your account”

	@Test(priority = 1)
	public void unknown_user_ticket_creation() throws InterruptedException, AWTException {

		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);

		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Assert.assertTrue(Login.getText().contains("Login"));

		if(driver.getPageSource().contains("Login")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		}
		Thread.sleep(1500);
		WebElement requester = driver.findElement(By.xpath("//input[@name='requester']"));
		requester.sendKeys("jai@outlook.com");

		Thread.sleep(2000);

		WebElement subject=driver.findElement(By.id("subject"));
		subject.click();

		WebElement subject1=driver.findElement(By.xpath("//input[@name='subject']"));
		Thread.sleep(2500);

		subject1.sendKeys("ticket is creating by unknown user");
		Thread.sleep(2000);

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

		driver.findElement(By.xpath("//button[@id='client_form_submit']")).click();
		Thread.sleep(1400);

		WebElement username = driver.findElement(By.xpath("//*[@id=\"Requester\"]/div[2]/div/div"));
		System.out.println(username.getText());

		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement elementLocated = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#Requester > div.col-md-10.flex > button > span")));
		elementLocated.click();
		Thread.sleep(2000);
		WebElement firstname = driver.findElement(By.name("first_name"));
		firstname.sendKeys("first name");

		driver.findElement(By.name("last_name")).sendKeys("jaya");   

		driver.findElement(By.name("email")).sendKeys("vedi9xx9r@outlook.com");

		Thread.sleep(2000);

		driver.findElement(By.cssSelector("#requester > div > div:nth-child(2) > div > div > div > div.modal-footer.justify-content-between > button.btn.btn-custom.text-white.pull-right")).click();
		try {
			WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"alert-message\"]/div/span")));
			String successText = successMessage.getText();


			System.out.println(successText);

		} catch (NoSuchElementException e) {
			Assert.fail("Success message not found");
		}

		JavascriptExecutor js2=(JavascriptExecutor)driver;		
		WebElement pagedown2=driver.findElement(By.xpath("//button[@id='client_form_submit']"));
		js2.executeScript("arguments[0].scrollIntoView(true);",pagedown2);

		driver.findElement(By.xpath("//button[@id='client_form_submit']")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement tktsuccessmessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Ticket has been created successfully')]")));

		// Print the dynamic success message
		System.out.println("Success message: " + tktsuccessmessage.getText());
		driver.close();
	}


	//Check for creating a contact while submitting the ticket
	//1.Access the URL
	//2.Click submit Ticket link
	//3.Enter all the mandatory fields
	//4.In the Requester field without enter the email id ,Click register button
	//5.In the register window enter all the mandatory field and click submit button

	//Expected Result: New contact added successfully message should be displayed





	@Test(priority = 2)
	public void createcontactwhilesubmittingticket() throws InterruptedException, AWTException  {
		Thread.sleep(1500);

		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);

		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Assert.assertTrue(Login.getText().contains("Login"));

		if(driver.getPageSource().contains("Login")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		}
		//		Thread.sleep(2000);
		//		WebElement subject=driver.findElement(By.id("subject"));
		//		subject.click();
		//
		//		WebElement subject1=driver.findElement(By.xpath("//input[@name='subject']"));
		//		Thread.sleep(2500);
		//
		//		subject1.sendKeys("ticket is creating by unknown user");
		//		Thread.sleep(2000);
		//
		//		WebElement priority = driver.findElement(By.id("priority_id"));
		//		priority.click();
		//
		//		Thread.sleep(1000);
		//
		//		Robot r = new Robot();
		//		r.keyPress(KeyEvent.VK_DOWN);
		//		r.keyRelease(KeyEvent.VK_DOWN);
		//		r.keyPress(KeyEvent.VK_ENTER);
		//		r.keyRelease(KeyEvent.VK_ENTER);
		//
		//		WebElement Helptopic = driver.findElement(By.xpath("//*[@id=\"help_topic_id\"]"));
		//		Helptopic.click();
		//
		//		Thread.sleep(500);
		//
		//		r.keyPress(KeyEvent.VK_ENTER);
		//		r.keyRelease(KeyEvent.VK_ENTER);
		//		Thread.sleep(1000);
		//
		//		JavascriptExecutor js=(JavascriptExecutor)driver;		
		//		WebElement pagedown=driver.findElement(By.xpath("//button[@id='client_form_submit']"));
		//		js.executeScript("arguments[0].scrollIntoView(true);",pagedown);
		//		WebElement type = driver.findElement(By.xpath("//*[@id=\"type_id\"]"));
		//		type.click();
		//
		//		Thread.sleep(1000);
		//
		//		r.keyPress(KeyEvent.VK_DOWN);
		//		r.keyRelease(KeyEvent.VK_DOWN);
		//		r.keyPress(KeyEvent.VK_ENTER);
		//		r.keyRelease(KeyEvent.VK_ENTER);
		//
		//		Thread.sleep(2000);
		//
		//		WebElement iframeElement = driver.findElement(By.tagName("iframe"));
		//		driver.switchTo().frame(iframeElement);
		//
		//		WebElement des = driver.findElement(By.cssSelector("#tinymce > p"));
		//		des.sendKeys("the rich text editor is within an iframe");
		//
		//		Thread.sleep(1000);
		//		driver.switchTo().defaultContent();
		//


		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement elementLocated = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#Requester > div.col-md-10.flex > button > span")));
		elementLocated.click();
		Thread.sleep(2000);

		WebElement firstname = driver.findElement(By.name("first_name"));
		firstname.sendKeys("first name");

		driver.findElement(By.name("last_name")).sendKeys("jaya");   

		driver.findElement(By.name("email")).sendKeys("jaisivameche7wsxcv4i@outlook.com");

		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#requester > div > div:nth-child(2) > div > div > div > div.modal-footer.justify-content-between > button.btn.btn-custom.text-white.pull-right")).click();
		try {
			WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"alert-message\"]/div/span")));
			String successText = successMessage.getText();


			System.out.println(successText);

		} catch (NoSuchElementException e) {
			Assert.fail("Success message not found");
		}
		driver.close();
	}









	@Test(priority = 3)
	//Check for create contact with already registered email in the submit ticket
	// Access the URl
	//Click submit ticket and clcik register 
	// fille the firstname and lastname and enter the already registered email id
	//Click submit button
	public void Already_registered_user() throws InterruptedException {
		Thread.sleep(1500);

		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);

		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Assert.assertTrue(Login.getText().contains("Login"));

		if(driver.getPageSource().contains("Login")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		}
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement elementLocated = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#Requester > div.col-md-10.flex > button > span")));
		elementLocated.click();
		Thread.sleep(2000);

		WebElement firstname = driver.findElement(By.name("first_name"));
		firstname.sendKeys("first name");

		driver.findElement(By.name("last_name")).sendKeys("jaya");   

		driver.findElement(By.name("email")).sendKeys("jaisivameche7wsxcv4i@outlook.com");

		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#requester > div > div:nth-child(2) > div > div > div > div.modal-footer.justify-content-between > button.btn.btn-custom.text-white.pull-right")).click();
		Thread.sleep(1000);
		WebElement email = driver.findElement(By.xpath("//*[@id=\"Email\"]/div[2]/div/div"));
		System.out.println(email.getText());


	}





	@Test(priority = 4)
	//Check for create contact and submit a ticket of type -Question
	//1.Access the URL
	//2.Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
	//3.Enter all the mandatory fields, Choose Ticket type as Question and click submit button
	public void createcontact_submitticket_type_question() throws InterruptedException, AWTException, TimeoutException{


		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);

		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Assert.assertTrue(Login.getText().contains("Login"));

		if(driver.getPageSource().contains("Login")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		}
		Thread.sleep(2000);
		WebElement subject=driver.findElement(By.id("subject"));
		subject.click();

		WebElement subject1=driver.findElement(By.xpath("//input[@name='subject']"));
		Thread.sleep(2500);

		subject1.sendKeys("ticket is creating by unknown user");
		Thread.sleep(2000);

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



		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement elementLocated = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#Requester > div.col-md-10.flex > button > span")));
		elementLocated.click();
		Thread.sleep(2000);

		WebElement firstname = driver.findElement(By.name("first_name"));
		firstname.sendKeys("first name");

		driver.findElement(By.name("last_name")).sendKeys("jaya");   

		driver.findElement(By.name("email")).sendKeys("jaisiech9vgr0nm9@outlook.com");

		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#requester > div > div:nth-child(2) > div > div > div > div.modal-footer.justify-content-between > button.btn.btn-custom.text-white.pull-right")).click();
		try {
			WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"alert-message\"]/div/span")));
			String successText = successMessage.getText();

			System.out.println(successText);

		} catch (NoSuchElementException e) {
			Assert.fail("Success message not found");
		}
		Thread.sleep(3000);

		JavascriptExecutor js1=(JavascriptExecutor)driver;		
		WebElement pagedown1=driver.findElement(By.xpath("//button[@id='client_form_submit']"));
		js1.executeScript("arguments[0].scrollIntoView(true);",pagedown1);

		Thread.sleep(2000);
		pagedown1.click();

		try {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement tktsuccessmessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Ticket has been created successfully')]")));

			// Print the dynamic success message
			System.out.println("Success message: " + tktsuccessmessage.getText());

		} 
		catch (NoSuchElementException e) {
			System.out.println("Success message not found or timed out");
		}
		driver.close();
	}



	@Test(priority = 5)
	public void Create_a_ticket_of_already_registered_user() throws AWTException, InterruptedException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);

		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Assert.assertTrue(Login.getText().contains("Login"));

		if(driver.getPageSource().contains("Login")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		}
		Thread.sleep(1500);
		WebElement requester = driver.findElement(By.xpath("//input[@name='requester']"));
		requester.sendKeys("jaisivamech@outlook.com");

		Thread.sleep(1400);
		WebElement subject=driver.findElement(By.id("subject"));
		subject.click();

		WebElement subject1=driver.findElement(By.xpath("//input[@name='subject']"));
		Thread.sleep(2500);

		subject1.sendKeys("ticket is creating by unknown user");
		Thread.sleep(2000);

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


		driver.findElement(By.xpath("//button[@id='client_form_submit']")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement tktsuccessmessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Ticket has been created successfully')]")));

		// Print the dynamic success message
		System.out.println("Success message: " + tktsuccessmessage.getText());
		driver.close();
	}



	@Test(priority = 6)
	//Negative scenarios for Submit ticket without user login
	// Check for error message if mandatory fields are not filled
	//Expected result: Proper error message should be displayed for each mandatory field

	public void Checkerror_message_mandatory_fields_are_not_filled() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();


		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);

		WebElement Login = driver.findElement(By.id("client_login"));//Login as client
		Assert.assertTrue(Login.getText().contains("Login"));

		if(driver.getPageSource().contains("Login")) {
			Thread.sleep(3000);
			WebElement clicksubmitticket = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));//Click 'Submit Ticket' link (Make sure Ticket summary drop down is displayed)
			clicksubmitticket.click();
		}
		Thread.sleep(2500);

		JavascriptExecutor js=(JavascriptExecutor)driver;		
		WebElement pagedown=driver.findElement(By.xpath("//button[@id='client_form_submit']"));
		js.executeScript("arguments[0].scrollIntoView(true);",pagedown);

		Thread.sleep(2000);

		WebElement submitButton = driver.findElement(By.id("client_form_submit"));
		submitButton.click();
		Thread.sleep(2000);
		WebElement requester = driver.findElement(By.xpath("//*[@id=\"Requester\"]/div[2]/div/span"));
		String requeter = requester.getText();
		System.out.println("Requester is required:" +requeter);
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

}
