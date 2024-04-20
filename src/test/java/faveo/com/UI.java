package faveo.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UI {
	WebDriver driver;
	@Test(priority=1)
     //Check for the list of items in home page without login
	 //1.Access the URL
	 //2.Verify the items in the Home page
	
	     //It should display below items in the home page:
		//1.In the header "Support center" should be displayed which is specified in the admin configuration
		//2.'Home, Submit Ticket,Knowledge Base with 2 Subitems :'Categories and Articles', Billing -Purchase packages, Login and Languages"
		//3.Search Text box with search button (Which is configured by Admin\Agent)
		//4."You are Here: Home" Breadcrumb with blue color(Configured by Admin\Agent)
		//5.Register,Submit Ticket,My ticketsKnowledge base -buttons
		//6.In the footer :"Copyright © 2023. cd All rights reserved."(configured by Admin\Agent)
	public void Check_for_list_of_items_inhomepage_withoutlogin() throws InterruptedException {
		WebDriverManager.chromedriver().setup();                         //Browser setup and launching the URL
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jaya.localhost/release/public/home");//Access the URL
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(1000);
		WebElement supportcenter = driver.findElement(By.xpath("//a[@class='router-link-active']"));
			System.out.println(supportcenter.getText());
			WebElement Home = driver.findElement(By.xpath("//a[@id='client_header_home_link']"));
			System.out.println(Home.getText());
			WebElement submittkt = driver.findElement(By.xpath("//a[@id='client_create_ticket']"));
			System.out.println(submittkt.getText());
			WebElement knowledgebase = driver.findElement(By.xpath("//a[@id='client_kb_link']"));
			System.out.println(knowledgebase.getText());
			
			Thread.sleep(500);
			
			WebElement categories = driver.findElement(By.xpath("//a[@id='client_kb_categories']"));
			WebElement Articles = driver.findElement(By.xpath("//a[@id='client_kb_articles']"));
			WebElement login = driver.findElement(By.xpath("//a[@id='client_login']"));
			WebElement language= driver.findElement(By.xpath("//a[@id='navbarDropdownLang']"));
			
			    Actions actions = new Actions(driver);
		        actions.moveToElement(knowledgebase).perform();
		        actions.moveToElement(categories).perform();
		        System.out.println(categories.getText());
		        actions.moveToElement(Articles).perform();
		        System.out.println(Articles.getText());
		        System.out.println(login.getText());
		        System.out.println(language.isDisplayed());
		        language.click();

		        // Locate all language elements within the dropdown
		        List<WebElement> languageElements = driver.findElements(By.xpath("//a[@class='lang dropdown-item']"));

		        // Check if all 18 languages are present
		        if (languageElements.size() == 18) {
		            System.out.println("All 18 languages are present.");
		        } else {
		            System.out.println("Some languages are missing.");
		        }
			
			
		}
		
			
		
		
	
}
