package faveo.com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Submiticketlarge {
	@Test(dataProvider = "subjectData")
public void FeatureRequest(String subString) throws InterruptedException, AWTException, IOException {
		
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
		subject1.sendKeys(subString);
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
		
	}
	 @DataProvider(name = "subjectData")
	    public Object[][] provideSubjectData() {
	        // Provide different sets of subject data for each iteration
	        return new Object[][]{
	                {"Subject 1"},
	                {"Subject 2"},
	                {"Subject 3"},{"subject 4"},{"சிறுத்தை நடமாட்டம்: மயிலாடுதுறையில் 9 பள்ளிகளுக்கு இன்று விடுமhttps://www.dailythanthi.com/News/State"},
	                {"new 1"},{"testing"},{"delicious"},{"automation ticket"},{"manik is the great man"},{"option type ticket creation"},{"sub"},{"surround speaker"},{"center speaker"},{"Rear speaker issue"},{"ceiling speaker"},{"fan"},{"bed"},{"good"},{"bad"},{"nice"},
	        };
	
}}
