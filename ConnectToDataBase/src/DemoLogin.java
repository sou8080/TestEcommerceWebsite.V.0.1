import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoLogin {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tania\\Desktop\\webdrivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		WebDriverWait driverwait = new WebDriverWait(driver, Duration.ofMillis(8000)); 
		driver.get("http://automationpractice.com/index.php");
		
		//Elemnet for sigin page
		WebElement testSignIn = driver.findElement(By.className("login"));
		testSignIn.click();
		
		WebElement textUsername = driver.findElement(By.id("email"));
		textUsername.sendKeys("xyzsou@gmail.com");
		Thread.sleep(2000);
		//textUsername.clear();
		
		WebElement textPassword = driver.findElement(By.name("passwd"));
		textPassword.sendKeys("NOTONLY");
		Thread.sleep(2000);
		//textPassword.clear();
		
		driverwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("SubmitLogin")));
		WebElement btnSubmit = driver.findElement(By.name("SubmitLogin"));
		btnSubmit.click();
		
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
		driver.close();
	}

}
