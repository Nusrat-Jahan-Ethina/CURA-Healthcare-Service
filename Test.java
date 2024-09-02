package CURA_Healthcare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//Book an appointment
public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Desktop\\Studies\\Drivers\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		
		Actions action = new Actions(driver);
		
		driver.findElement(By.id("btn-make-appointment")).click();
		
		//Login
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		action.sendKeys(Keys.ENTER).perform();
		//driver.findElement(By.id("btn-login")).click();
		
		//Facility (drop down)
		WebElement fac = driver.findElement(By.id("combo_facility"));
		
		Select f1 = new Select(fac);
		
		List <WebElement> f = f1.getOptions();
		
		for (int i = 0; i<f.size(); i++) {
			
			f1.selectByIndex(i);
			Thread.sleep(1500);
			
		}
		
		//Checkbox
		driver.findElement(By.id("chk_hospotal_readmission")).click();
		
		//Healthcare Program (radio button)
		List <WebElement> hp = driver.findElements(By.className("radio-inline"));
		
		for (int i=0; i<hp.size(); i++) {
			
			hp.get(i).click();
			Thread.sleep(1500);
			
		}
		
		//Visit date (date picker)
		driver.findElement(By.id("txt_visit_date")).click();
		
		String cMonth = driver.findElement(By.className("datepicker-switch")).getText();
		
		while (!(cMonth.equals ("December 2024"))) {
			
			driver.findElement(By.className("next")).click();
			cMonth = driver.findElement(By.className("datepicker-switch")).getText();
			
		}
		
		driver.findElement(By.xpath("//td[contains(text(),'11')]")).click();
		
		//Comment box
		WebElement cmnt = driver.findElement(By.id("txt_comment"));
		action.moveToElement(cmnt).click().sendKeys("Heart patient").build().perform();
		Thread.sleep(1000);
		
		//submit
		driver.findElement(By.id("btn-book-appointment")).click();
	}
}
