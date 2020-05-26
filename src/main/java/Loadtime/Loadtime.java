package Loadtime;

import org.openqa.selenium.remote.RemoteWebDriver;


import org.testng.annotations.Test;

import java.net.URL;
import io.restassured.RestAssured;

import java.io.File;
import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


@Test
public class Loadtime extends DefaultHandler {
	
  static Capabilities chromeCapabilities = DesiredCapabilities.chrome();
  static Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
  
  static String URL = "http://ecdev01apps.telkom.co.za/access-manager";
  static int responsecode = 0;
  
  static RemoteWebDriver chrome = null;
  static RemoteWebDriver firefox = null;

  
  //Response code method, returns an integer.
  public static int httpResponseCode(String url) {
	      return RestAssured.get(url).statusCode();
	}	
  
  

  public static void main() {
   
   
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

 //Reading xml file to return the url value from Jenkins
   try {
		  
		  File f = new File("C://Program Files (x86)//Jenkins//jobs//TestNg//config.xml");
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
			  
			  
			  public  void characters(char[] ch, int start, int length) throws SAXException
			  {
				  
				  if(desc) {
					  
					  // run against chrome
					  chrome.get(new String(ch,start, length));
					  System.out.println(chrome.getTitle());
					  
					  // run against firefox
					  firefox.get(new String(ch,start, length));
					  System.out.println(firefox.getTitle());
					  
					  //get http response code
					  responsecode = httpResponseCode(new String(ch,start, length));
					  System.out.println("HTTP Code: " + responsecode);
					  
					  //close both browsers
					  chrome.quit();
					  firefox.quit();
					  
					  
					  desc = false;
				  }
				  
			  }
		  };
		  
		  parser.parse(f, handler);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	

    // run against chrome
    //chrome.get("https://www.google.com");
    //System.out.println(chrome.getTitle());
    //responsecode = httpResponseCode("https://www.google.com");
    //System.out.println("HTTP Code: " + responsecode);

    // run against firefox
   // firefox.get(URL);
    //System.out.println(firefox.getTitle());
    //System.out.println("HTTP Code: " + responsecode + getUrl());
  
    //chrome.quit();
   // firefox.quit();
  }
}