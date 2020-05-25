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
  //static String URL;

  
  //Response code method, returns an integer.
  public static int httpResponseCode(String url) {
	      return RestAssured.get(url).statusCode();
	}	
  
  
  /*@Test(enabled = false)
  public static void setUrl(String url) {
	  URL = url;
  }
  */
  
  public static String getUrl() {return URL;}

  public static void main() {
	  
   RemoteWebDriver chrome = null;
   RemoteWebDriver firefox = null;
   
   int responsecode = 0;

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
					String value = attr.getValue(qName);
					System.out.println("Description: " + value);
					desc = true;
				}
					
			  }
			  
			  public void endElement(String uri, String localName, String qName) {
				  
				  if(qName.equals("description")) {
					  //System.out.println("Description: " + qName);
					  desc = false;
				  } 
			  }
			  
			  
			  public  void characters(char[] ch, int start, int length) throws SAXException
			  {
				  
				  if(desc) {
					  System.out.println("description is : " + new String(ch,start, length));
					  desc = false;
				  }
				  
			  }
		  };
		  
		  parser.parse(f, handler);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
   
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
	
    responsecode = httpResponseCode(URL);

    // run against chrome
    chrome.get(URL);
    System.out.println(chrome.getTitle());
    System.out.println("HTTP Code: " + responsecode);

    // run against firefox
    firefox.get(URL);
    System.out.println(firefox.getTitle());
    System.out.println("HTTP Code: " + responsecode + getUrl());
  
    chrome.quit();
    firefox.quit();
  }
}