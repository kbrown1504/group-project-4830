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

public class CreateListingTest {
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
  public void testCreateListing() throws Exception {
    driver.get("http://unodamme.ddns.net:8080/group-project-4830/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testuser10");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("testpassword");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//button[@onclick=\"window.location.href='createListing'\"]")).click();
    driver.findElement(By.name("title")).click();
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("TestTitle10");
    driver.findElement(By.name("author")).clear();
    driver.findElement(By.name("author")).sendKeys("Test Author");
    driver.findElement(By.name("isbn")).clear();
    driver.findElement(By.name("isbn")).sendKeys("9876543219876");
    driver.findElement(By.name("price")).click();
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("105");
    driver.findElement(By.id("condition")).click();
    new Select(driver.findElement(By.id("condition"))).selectByVisibleText("Good");
    driver.findElement(By.name("addinfo")).click();
    driver.findElement(By.name("addinfo")).clear();
    driver.findElement(By.name("addinfo")).sendKeys("None");
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
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
