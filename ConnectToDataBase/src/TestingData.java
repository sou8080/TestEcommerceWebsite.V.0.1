import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestingData
{

	public static void main(String[] args) throws InterruptedException
	{
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tania\\Desktop\\webdrivers\\chromedriver.exe");		// path for the chromedriver
	
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize(); 	// for maximizing the chrome window
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);		 // using implicit wait for the whole page to load
		//1.Going to Web urls
		driver.get("http://automationpractice.com/index.php");
		
		WebDriverWait driverwait = new WebDriverWait(driver, Duration.ofSeconds(20)); 	 // Creating explicit wait class for the element
		//Element for sign in page
		WebElement testSignIn = driver.findElement(By.className("login"));	 // Finding element by classname 
		testSignIn.click();
				
			try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","sou1234");  // path for the local database (mysql)
				
					System.out.println("connect to db");  // Printing for successfully connecting through database
				
					String sql = "SELECT * FROM testingdb.souviklogin";		//Query
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
				
					while(rs.next())
					{
						System.out.println(rs.getString(1) + "  " + rs.getString(2));  // Printing Elements Existing in databases
						
						
						
						driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
						WebElement textUsername = driver.findElement(By.id("email")); // Finding Element by id 
						textUsername.sendKeys(rs.getString(1));  // sending keys through databases
						
						Thread.sleep(2000);
						
						driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("passwd")));
						WebElement textPassword = driver.findElement(By.name("passwd")); 	// Finding element by name 
						textPassword.sendKeys(rs.getString(2));  	// sending keys through databases
					
						driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("SubmitLogin"))); // waiting for button element to click 		
						WebElement btnSubmit = driver.findElement(By.name("SubmitLogin"));
						btnSubmit.click();
						
						driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
						if (rs.getString(1).contentEquals("xyzsou@gmail.com") && rs.getString(2).contentEquals("NOTONLY")) 
						{
							break;
						}
						else
						{
							try
							{
								driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
								Thread.sleep(2000);
								textUsername.clear();	 // To clear the text in the username textbox
						
								driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("passwd")));
								Thread.sleep(2000);
								textPassword.clear(); 	// To clear the text in password textbox					
							}
							catch(StaleElementReferenceException e)
							{
								driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
								textUsername = driver.findElement(By.id("email")); 
								textUsername.clear();  
							
								Thread.sleep(2000);
							
								driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("passwd")));
								textPassword = driver.findElement(By.name("passwd")); 	// Finding element by name 
								textPassword.clear();  
							
							}
						}
					}
				} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			WebElement txtboxSearch = driver.findElement(By.id("search_query_top"));
			txtboxSearch.sendKeys("dresses");
			txtboxSearch.submit();
			
			WebElement dropdwnSort = driver.findElement(By.id("selectProductSort"));
			
			Select selectsortby = new Select(dropdwnSort);
			selectsortby.selectByIndex(1);
			
			WebElement getproduct =  driver.findElement(By.linkText("Faded Short Sleeve T-shirts"));
			getproduct.click();
			
			WebElement addtobtn = driver.findElement(By.name("Submit")); // adding items to cart
			addtobtn.click();
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			
			WebElement ptochck = driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a > span"));
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ptochck);
			
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			WebElement orderpagechkout = driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span"));
			Thread.sleep(2000);
			orderpagechkout.click();
			
			WebElement addresspagechkout = driver.findElement(By.cssSelector("#center_column > form > p > button > span"));
			Thread.sleep(2000);
			addresspagechkout.click();
			
			WebElement testcheckbox = driver.findElement(By.cssSelector("#cgv"));
			testcheckbox.click();
			Thread.sleep(2000);
			
			WebElement shippingpagechkout = driver.findElement(By.cssSelector("#form > p > button > span"));
			Thread.sleep(2000);
			shippingpagechkout.click();
			
			WebElement paymentpage = driver.findElement(By.partialLinkText("Pay by bank wire"));
			Thread.sleep(2000);
			paymentpage.click();
			
			WebElement confirmOrderPage = driver.findElement(By.cssSelector("#cart_navigation > button > span"));
			Thread.sleep(2000);
			confirmOrderPage.click();
			
			Thread.sleep(4000);
			
			driver.close(); // for closing the chrome browser
			
	}

}

