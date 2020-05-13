package Loadtime;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTest {
	
  String driverPath = "";
  public String baseUrl = "{name_of_env}apps.telkom.co.za/{name_of_application}";
  public WebDriver driver;
  
  @BeforeTest
  public void launchBrowser() {
     // System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Files" + File.separator + "chromedriver_win32" + File.separator + "chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver",System.getProperty("user.home") + File.separator + "AppData" + File.separator + "chromedriver_win32" + File.separator + "chromedriver.exe" ); 
      driver = new ChromeDriver();
      driver.get("www.google.com");
  }	

  @Test
  public void urlLogin() {
	  
  }

  @AfterTest
  public void closeBrowser() {
	  
  }

}
