package test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.apache.commons.io.FileUtils;
import java.io.File;

public class CheckoutTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dillon\\Documents\\ChromeDriver\\chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testCheckout() throws Exception {
    driver.get("http://unodamme.ddns.net:8080/group-project-4830/login");
    driver.get("http://unodamme.ddns.net:8080/group-project-4830/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testuser10");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("testpassword");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.name("search")).click();
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("heuristic");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("heuristic")).click();
    driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
    driver.findElement(By.xpath("//button[@onclick=\"window.location.href='Cart'\"]")).click();
    driver.findElement(By.xpath("//button[@onclick=\"window.location.href='OrderConfirmation'\"]")).click();
    driver.findElement(By.name("fullName")).click();
    driver.findElement(By.name("fullName")).clear();
    driver.findElement(By.name("fullName")).sendKeys("Test Name");
    driver.findElement(By.name("streetAddress")).clear();
    driver.findElement(By.name("streetAddress")).sendKeys("1234 Test St");
    driver.findElement(By.name("city")).clear();
    driver.findElement(By.name("city")).sendKeys("TestCity");
    driver.findElement(By.name("state")).clear();
    driver.findElement(By.name("state")).sendKeys("TestState");
    driver.findElement(By.name("zipCode")).clear();
    driver.findElement(By.name("zipCode")).sendKeys("12345");
    driver.findElement(By.name("cardNumber")).clear();
    driver.findElement(By.name("cardNumber")).sendKeys("12345");
    driver.findElement(By.name("nameOnCard")).click();
    driver.findElement(By.name("nameOnCard")).clear();
    driver.findElement(By.name("nameOnCard")).sendKeys("Test Name");
    driver.findElement(By.name("expirationDate")).clear();
    driver.findElement(By.name("expirationDate")).sendKeys("01/2022");
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
    Assert.assertTrue(driver.findElements(By.xpath("//h1[text()='BookWorms - My Account']")).size() == 1);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
