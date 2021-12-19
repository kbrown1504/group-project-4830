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

public class BasicSearchTest {
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
  public void testBasicSearch() throws Exception 
  {
	int numBooks = 0;
	
	driver.get("http://unodamme.ddns.net:8080/group-project-4830/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testuser10");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("testpassword");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.name("search")).click();
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("emulation");
    driver.findElement(By.name("submit")).click();
    numBooks = driver.findElements(By.xpath("//img")).size();
    Assert.assertEquals(2, numBooks);
    
    driver.findElement(By.id("categories")).click();
    new Select(driver.findElement(By.id("categories"))).selectByVisibleText("Author");
    driver.findElement(By.name("search")).click();
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("Hubber");
    driver.findElement(By.name("submit")).click();
    numBooks = driver.findElements(By.xpath("//img")).size();
    Assert.assertEquals(1, numBooks);
    
    driver.findElement(By.id("categories")).click();
    new Select(driver.findElement(By.id("categories"))).selectByVisibleText("ISBN");
    driver.findElement(By.xpath("//form[@action='search']")).click();
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("9785896370598");
    driver.findElement(By.name("submit")).click();
    numBooks = driver.findElements(By.xpath("//img")).size();
    Assert.assertEquals(1, numBooks);
    
    driver.findElement(By.name("search")).click();
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("");
    driver.findElement(By.name("submit")).click();
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
