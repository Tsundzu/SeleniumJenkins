package Loadtime;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;
import io.restassured.RestAssured;

import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Test
public class Loadtime {
  static Capabilities chromeCapabilities = DesiredCapabilities.chrome();
  static Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
	  
  //Response code method, returns an integer.
  public static int httpResponseCode(String url) {
	      return RestAssured.get(url).statusCode();
	}	
  
  //Reading xml value to return the url value from Jenkins
  public static String getUrl() {
	  
	  String xmlFile = "employees.xml";
	  DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
      try {
          DocumentBuilder builder = domFactory.newDocumentBuilder();
          Document dDoc = builder.parse("C:\\Program Files (x86)\\Jenkins\\jobs\\TestNg\\config.xml");

          XPath xPath = XPathFactory.newInstance().newXPath();
          Node node = (Node) xPath.evaluate("project/description", dDoc, XPathConstants.NODE);
          System.out.println(node.getNodeValue());
      } catch (Exception e) {
          e.printStackTrace();
      }
	return null;
	  
  }

  

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
    System.out.println("HTTP Code: " + responsecode + getUrl());
  
    chrome.quit();
    firefox.quit();
  }
}