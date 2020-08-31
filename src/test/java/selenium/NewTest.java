package selenium;

import org.openqa.selenium.remote.RemoteWebDriver;



import org.testng.annotations.Test;

import java.net.URL;
import io.restassured.RestAssured;

import java.io.File;
import java.net.MalformedURLException;
//import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;



public class NewTest extends DefaultHandler {
	
  //define static variables
	static ChromeOptions chromeCapabilities = new ChromeOptions();
	static FirefoxOptions firefoxCapabilities = new FirefoxOptions();
	  
	static String ulr = null;
	static int responsecode = 0;
	static boolean desc = false;
	
	static RemoteWebDriver chrome = null;
	static RemoteWebDriver firefox = null;
	
  //define static method to return(integer) http status code
    public static int httpResponseCode(String url) {
	      return RestAssured.get(url).statusCode();
	}
    
    
  //TestNG test method
  @Test
  public void seleniumTest() {
	  try {
		  //file location from which to extract URL
		  File f = new File("C://Program Files (x86)//Jenkins//jobs//TestNg//config.xml");
		  //parser for XML file
		  SAXParserFactory fact = SAXParserFactory.newInstance();
		  SAXParser parser = fact.newSAXParser();
		  
		  DefaultHandler handler =  new DefaultHandler() {
			  boolean desc = false;
			  
			  public void startElement(String uri, String localName, String qName,
					  Attributes attr) throws SAXException{
				
				if(qName.equals("description")) {
					//String value = attr.getValue(qName);
					desc = true;
				}
					
			  }
			  
			  public void endElement(String uri, String localName, String qName) {
				  
				  if(qName.equals("description")) {
					  desc = false;
				  } 
			  }
			  
			  //get the characters of the URL and turn to String
			  public  void characters(char[] ch, int start, int length) throws SAXException
			  {
				  
				  if(desc) {
					  
					  // run against Chrome
					  chrome.get(new String(ch,start, length));
					  System.out.println("Chrome Browser: " + chrome.getTitle());
					  
					//get http response code
					  responsecode = httpResponseCode(new String(ch,start, length));
					  System.out.println("HTTP Status Code: " + responsecode);
					  
					  // run against firefox
					  firefox.get(new String(ch,start, length));
					  System.out.println("Firefox Browser: " + firefox.getTitle());
					  
					  //get http response code
					  responsecode = httpResponseCode(new String(ch,start, length));
					  System.out.println("HTTP Status Code: " + responsecode);
					  
					  
					  desc = false;
				  }
				  
			  }
		  };
		  
		  parser.parse(f, handler);
		  
	  }
	  catch (Exception ex) {
		  
	  }
  }
  
  @BeforeTest
  public void beforeTest() {
	  
	  //chromeCapabilities.setBinary(new File(""));
	//create remote chrome driver to connect to browser on the url
		try {
			chromeCapabilities.setBinary(new File(""));
			chrome = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		}
		//create remote firefox driver to connect to browser on the url
		try {
			firefox = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxCapabilities);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		
  }

  @AfterTest
  public void afterTest() {
	  chrome.quit();
	  firefox.quit();
  }

}
