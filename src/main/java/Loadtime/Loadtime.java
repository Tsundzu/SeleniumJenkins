package Loadtime;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;
import io.restassured.RestAssured;
import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

@Test
public class Loadtime {
	
  public static int httpResponseCode(String url) {
	      return RestAssured.get(url).statusCode();
	}	

  static Capabilities chromeCapabilities = DesiredCapabilities.chrome();
  static Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
  

  public static void main() {
   RemoteWebDriver chrome = null;
   RemoteWebDriver firefox = null;
   String URL = "http://ecdev01apps.telkom.co.za/access-manager";
   
   int responsecode = 0;
   responsecode = httpResponseCode(URL);
   
   //Testing against chrome browser
	try {
		chrome = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    //Testing against firefox
	try {
		firefox = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxCapabilities);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // run against chrome
    chrome.get(URL);
    System.out.println(chrome.getTitle());
    System.out.println("HTTP Code: " + responsecode);

    // run against firefox
    firefox.get(URL);
    System.out.println(firefox.getTitle());
    System.out.println("HTTP Code: " + responsecode);
  
    chrome.quit();
    firefox.quit();
  }
}