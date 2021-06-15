package com.demoqa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class UserCreationTests {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--debug");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUserCreation() throws Exception {
    driver.get("https://demoqa.com/");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Interactions'])[1]/following::*[name()='svg'][1]")).click();
    //createNewUser();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Book Store Application'])[1]/following::span[1]")).click();
    driver.findElement(By.id("userName")).click();
    driver.findElement(By.id("userName")).clear();
    driver.findElement(By.id("userName")).sendKeys("TestUser");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("TestUser12345!");
    driver.findElement(By.id("login")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Login'])[1]/following::span[1]")).click();
    driver.findElement(By.linkText("Git Pocket Guide")).click();
    driver.findElement(By.xpath("//div[@id='app']/div/div/div[2]/div[2]/div/div[2]/div[9]/div[2]/button")).click();
    assertEquals(closeAlertAndGetItsText(), "Book added to your collection.");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Book Store'])[2]/following::span[1]")).click();
    driver.findElement(By.linkText("Git Pocket Guide")).click();
    driver.findElement(By.id("addNewRecordButton")).click();
    driver.findElement(By.id("submit")).click();
  }

  private void createNewUser() {
    driver.findElement(By.xpath("//div[@id='app']/div/div/div[2]/div/div/div/div[6]/div/ul/li")).click();
    driver.findElement(By.id("newUser")).click();
    driver.findElement(By.id("firstname")).click();
    driver.findElement(By.id("firstname")).clear();
    driver.findElement(By.id("firstname")).sendKeys("TestUser");
    driver.findElement(By.id("lastname")).click();
    driver.findElement(By.id("lastname")).clear();
    driver.findElement(By.id("lastname")).sendKeys("TestUser");
    driver.findElement(By.id("userName")).click();
    driver.findElement(By.id("userName")).clear();
    driver.findElement(By.id("userName")).sendKeys("TestUser");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("TestUser12345!");
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | index=0 | ]]
    driver.findElement(By.xpath("//span[@id='recaptcha-anchor']/div[4]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | relative=parent | ]]
    driver.findElement(By.id("register")).click();
    assertEquals(closeAlertAndGetItsText(), "User Register Successfully.");
  }

  @AfterClass(alwaysRun = true)
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
