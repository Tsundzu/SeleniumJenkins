package Loadtime;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class Loadtime {
	
  String driverPath = "";
  public String baseUrl = "{name_of_env}apps.telkom.co.za/{name_of_application}";
  public WebDriver driver;
  
  @BeforeTest
  public void launchBrowser() {
      System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,"C:" + File.separator + "Users" + File.separator + "Tsu" + File.separator + "Desktop" + File.separator + "Files" + File.separator + "chromedriver_win32" + File.separator + "chromedriver.exe");
      driver = new ChromeDriver();
  }	

  @Test
  public void urlLogin() {
	  driver.get("https://www.google.com/");
  }

  @AfterTest
  public void closeBrowser() {
	  driver.quit();
  }

}