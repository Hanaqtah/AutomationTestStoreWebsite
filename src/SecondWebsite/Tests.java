package SecondWebsite;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Tests extends Data{
	
	String website = "https://automationteststore.com/";
	String SignUpPage = "https://automationteststore.com/index.php?rt=account/create";
	
	WebDriver driver = new EdgeDriver(); 
	
	
	@BeforeTest
	public void SetUp() {
		
		driver.get(website);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@Test(priority=1, enabled=false)
	public void SignUp() throws InterruptedException {
		
		// opening signup page
		
		// using the signup page link directly
			driver.navigate().to(SignUpPage); 
			
		// using the login or register button in menue to choose from
			//driver.findElement(By.linkText("Login or register")).click();
		// clicking on the continue button to open signup page
			//WebElement SignUpBtn = driver.findElement(By.xpath("//button[@title='Continue']"));
			//SignUpBtn.click();
		
		// obtaining web elements 
			WebElement FirstName = driver.findElement(By.id("AccountFrm_firstname"));
			WebElement LastName = driver.findElement(By.id("AccountFrm_lastname"));
			WebElement EMail = driver.findElement(By.id("AccountFrm_email"));
			WebElement Address1 = driver.findElement(By.id("AccountFrm_address_1"));
			WebElement ZipCode = driver.findElement(By.id("AccountFrm_postcode"));
			WebElement City = driver.findElement(By.id("AccountFrm_city"));
			WebElement LoginName = driver.findElement(By.id("AccountFrm_loginname"));
			WebElement Password = driver.findElement(By.id("AccountFrm_password"));
			WebElement PasswordConfirmation = driver.findElement(By.id("AccountFrm_confirm"));
			WebElement FAX = driver.findElement(By.id("AccountFrm_fax"));
			WebElement TelePhone = driver.findElement(By.id("AccountFrm_telephone"));
			WebElement CheckBoxBtn = driver.findElement(By.id("AccountFrm_agree"));
			WebElement ContinueBtn = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
			
		// these have a dropdown muneu to select from
			WebElement CountryField = driver.findElement(By.id("AccountFrm_country_id"));
			WebElement StateField = driver.findElement(By.id("AccountFrm_zone_id"));
			
		// Select elements
			Select Country = new Select (CountryField);
			Select State = new Select (StateField);
		
		// actions
			FirstName.sendKeys(fname);
			LastName.sendKeys(Lname);
			EMail.sendKeys(FullMail);
			ZipCode.sendKeys(ZIPCode);
			//Address1.sendKeys(Addrs);
			FAX.sendKeys(fax);
			TelePhone.sendKeys(Phone);
			LoginName.sendKeys(FullLoginName);
			Password.sendKeys(pass);
			PasswordConfirmation.sendKeys(ConfPass);
		
			Country.selectByIndex(108);
		
		// fill the city based on state
			Thread.sleep(2000);
			State.selectByIndex(theSelectStateIndex);

		// obtaining all options in select to access them 
		// we obtain them from the web element not the select item itself
			List<WebElement> AlltheStates = StateField.findElements(By.tagName("option"));
			List <WebElement> AllCountries = CountryField.findElements(By.tagName("option"));
	
		// fill the city based on state
			String theCity = AlltheStates.get(theSelectStateIndex).getText();
			String theCountry = AllCountries.get(108).getText();
			City.sendKeys(theCity);
			
		// Address based on country & state
			Address1.sendKeys(theCountry + " " + theCity);
		
		// three ways to select an option from a select muneu
			//Country.selectByIndex(108);
			//Country.selectByValue("106");
			//Country.selectByValue("France");
			
			CheckBoxBtn.click();
			ContinueBtn.click();

			Thread.sleep(3000);
		// obtaining actual displayed msg
			String ActualSignUpMsg = driver.findElement(By.className("maintext")).getText();
		// compares the expected one with actual one and the result is reflected on the pass or fail in console
			Assert.assertEquals(ActualSignUpMsg, ExpectedSignUpMsg);	
	}
	
	
	@Test(priority=2, enabled=false)
	public void LogOutTest()
	{
		// this search for an element of type a with text of logoff
			WebElement LogoutBtn = driver.findElement(By.linkText("Logoff"));
			LogoutBtn.click();

		//this search for an element of type a with part of its text
			//driver.findElement(By.partialLinkText("Logo")).click();
		
		// searchin page if the message exists in it
			boolean ActualLogoutText = driver.getPageSource().contains(ExpectedLogOutMsg);
			
		// comparing the actual with what i expect to be true (msg exists)
			Assert.assertEquals(ActualLogoutText, true);
			
			// Assert.assertEquals(ActualLogoutText, true);
			//Assert.assertEquals(driver.findElement(By.className("heading1")).getText(),ExpectedLogOutMsg2 );
	}
	
	@Test(priority=3, enabled=false)
	public void LogInTest() throws InterruptedException
	{
		// using the login or register button in menue to choose from
			driver.findElement(By.linkText("Login or register")).click();
			
		// accessing elements required for login
			WebElement LoginName = driver.findElement(By.id("loginFrm_loginname"));
			WebElement Password = driver.findElement(By.id("loginFrm_password"));
		
		// filling info for  required for login
			Thread.sleep(3000);
			LoginName.sendKeys(FullLoginName);
			Password.sendKeys(pass);
			
		// clicking on the continue button to open signup page
			WebElement LogInBtn = driver.findElement(By.xpath("//button[@title='Login']"));
			LogInBtn.click();
			
			Thread.sleep(2000);
	
			boolean ActualVlaue = driver.getPageSource().contains(WelcomeMessage);
			
		 	System.out.println(ActualVlaue);
		 	System.out.println(WelcomeMessage);
		 	
	
		 	Assert.assertEquals(ActualVlaue, true);
	
	}

	@Test(priority = 4)
	public void AddItemToThecart() throws InterruptedException {
		
		// to open home page which contains products
			driver.navigate().to(website);
	    
		// to ensure all items loaded	
			Thread.sleep(2000);
		
		// SOLUTION 1
		/*
		 * for (int i=0; i<10; i++) 
		 *	{
		 *  // gathering all elements in one place
		 *		List<WebElement> AllItems = driver.findElements(By.className("prdocutname"));
		 *
		 *	// accessing random elements
		 *		int RandomIndexForTheItem = rand.nextInt(AllItems.size());
		 *
		 *	// clicking on item to view its description page
		 *		AllItems.get(RandomIndexForTheItem).click();
			
			// checking the problematic products 
		 *		boolean OutOfStock = driver.getPageSource().contains("Out of Stock");
		 *		boolean SizeRequired = driver.getCurrentUrl().contains("product_id=116");
			
		 *	if (!OutOfStock && !SizeRequired )
		 *	{
		 *	// adding items 
		 *		WebElement AddToCartBtn = driver.findElement(By.cssSelector(".cart"));
		 *		AddToCartBtn.click();
		 *		break;
		 *	}
		 *	// back to products page
		 *		driver.navigate().back();
		 *	}		
		 */
		
		// SOLUTION 2
		/*
		 * for (int i=0; i<10; i++) { 
		 * List<WebElement> AllItems =driver.findElements(By.className("prdocutname")); 
		 * int RandomIndexForTheItem = rand.nextInt(AllItems.size()); 
		 * AllItems.get(RandomIndexForTheItem).click();
		 * 
		 * boolean OutOfStock = driver.getPageSource().contains("Out of Stock"); 
		 * boolean SizeRequired = driver.getCurrentUrl().contains("product_id=116");
		 * 
		 * if (OutOfStock || SizeRequired ){ 
		 * 	driver.navigate().back(); 
		 * 	} 
		 * else {
		 * 	WebElement AddToCartBtn = driver.findElement(By.cssSelector(".cart"));
		 * 	AddToCartBtn.click(); 
		 * 	break; 
		 * 	} 
		 * }
		 */
	    
		
		// SOLUTION 3
		/*
		 * List<WebElement> AllItems = driver.findElements(By.className("prdocutname"));
		 * int RandomIndexForTheItem = rand.nextInt(AllItems.size());
		 * AllItems.get(RandomIndexForTheItem).click();
		 * 
		 * for(int i=0; i<10; i++) 
		 * { 
		 * 	if (driver.getPageSource().contains("Out of Stock") || driver.getCurrentUrl().contains("product_id=116")) 
		 * 		{
		 * 			driver.navigate().back();
		 * 
		 * 			List<WebElement> Allproducts = driver.findElements(By.className("prdocutname"));
		 * 			Allproducts.get(rand.nextInt(Allproducts.size())).click(); 
		 * 		}
		 * }
		 * 
		 * WebElement AddToCartBtn = driver.findElement(By.cssSelector(".cart"));
		 * AddToCartBtn.click();
		 */
			
			
		// item 9 selecting a random size
		/*
		 * // checking if theres required info for an item
		 * 		boolean requiredInfo = driver.getPageSource().
		 * 		contains("UK size: Please select all required options!");
		 * 		System.out.println(requiredInfo);
		 *	 
		 *		 if(requiredInfo) { 
		 * 			// gathering all options of size List <WebElement>
		 * 				AllSizes = driver.findElements(By.xpath("//input[@name='option[344][]']"));
		 * 
		 * 			// selecting a random size 
		 * 				Random rand2 = new Random(); 
		 * 				int RandomSize =rand2.nextInt(AllSizes.size()); 
		 * 				Thread.sleep(3000);
		 * 				AllSizes.get(RandomSize).click(); 
		 * 	}
		 */
			
			
			
		System.out.println(driver.getCurrentUrl());
	}
	
	
	@AfterTest
	public void AfterTest() {
		
		// this closes the currently opened tab
			//driver.close();
		// this closes all currently opened tabs
			//driver.quit();
			
	}
		

}
