package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import executionEngine.DriverScript;
import utility.Log;

public class ActionKeywords {
	
		public static WebDriver driver;
		public static WebElement element;
		public static String value;
			
	public static void openBrowser(String object,String data){		
		Log.info("Opening Browser");
		try{				
			if(data.equals("Firefox")){
				driver=new FirefoxDriver();
				Log.info("Firefox browser started");				
				}
			else if(data.equals("IE")){
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
				}
			else if(data.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Li\\JavaWorkSpace\\Ethoca_KeywordDrivenFramework\\src\\utility\\chromedriver.exe"); 
				driver=new ChromeDriver();
				Log.info("Chrome browser started");
				}
			
			driver.manage().window().maximize();
			int implicitWaitTime=(3);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch (Exception e){
			Log.info("Not able to open the Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScript.bResult = false;
			}
		}

    public static WebElement locateElement(String object, String data){
		try{
			if(data.equals("className")){
		        element = driver.findElement(By.className(OR.getProperty(object)));
			    Log.info("Find element by className");	
			}
		    else if(data.equals("linkText")){
	            element = driver.findElement(By.linkText(OR.getProperty(object)));
		        Log.info("Found element by linkText");	
			}
		    else if(data.equals("name")){
	            element = driver.findElement(By.name(OR.getProperty(object)));
		        Log.info("Found element by name");	
			}
		    else if(data.equals("xpath")){
	            element = driver.findElement(By.xpath(OR.getProperty(object)));
		        Log.info("Found element by xpath");	
			}
			else if(data.equals("")){
		        element = driver.findElement(By.id(OR.getProperty(object)));
			    Log.info("Found element by default type of id");	
				}
	
		 }catch(Exception e){
 			Log.error("Did not locate element --- " + e.getMessage());
 			DriverScript.bResult = false;
          	}
		return element;
		}
	
	
	public static void move(String object, String data){
		WebElement element = locateElement(object, data);
		try{
			Actions action = new Actions(driver);  
	        action.moveToElement(element).build().perform();
			Log.info("Moving mouse to dropdown menu "+ object);
		
		 }catch(Exception e){
 			Log.error("Not able to move to --- " + e.getMessage());
 			DriverScript.bResult = false;
         	}
		}
	
	public static void click(String object, String data){
		WebElement element = locateElement(object, data);
		try{
			Log.info("Clicking on Web Element "+ object);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			element.click();
		 }catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			DriverScript.bResult = false;
         	}
		}
	
	public static void input(String object, String data){
		try{
			Log.info("Entering the text in " + object);
			driver.findElement(By.id(OR.getProperty(object))).sendKeys(data);
		 }catch(Exception e){
			 Log.error("Not able to Enter UserName --- " + e.getMessage());
			 DriverScript.bResult = false;
		 	}
		}

	public static void select(String object, String data){
		try{
			Log.info("Selecting the option from " + object);
			WebElement country = driver.findElement(By.id(OR.getProperty(object)));
		    Select cSelection = new Select(country);
		    cSelection.selectByVisibleText(data);

		 }catch(Exception e){
			 Log.error("Not able to select option due to " + e.getMessage());
			 DriverScript.bResult = false;
		 	}
		}
	
	
	public static void compareValue(String object, String data){
		String value = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
	      Log.info("It is going to compare the value with" + data);
		try{
			Log.info("Compare value of web element " + object);
			if (value.equals(data)){
			Log.info("The expect result equals actual result on " + object);
			}
			else {
			Log.info("The expect result doesn't equal actual result on " + object);
			 DriverScript.bResult = false;
			}
		 }catch(Exception e){
			 Log.error("Not able to Enter UserName due to " + e.getMessage());
			 DriverScript.bResult = false;
		 	}
		}
	
	public static void compareContent(String object, String data){
	      String result = driver.getPageSource();
	      Log.info("It is going to compare the page content with " + data);
	      if (result.contains(data)) {
	    	  Log.info("The page content validation is passed");
	      }
	      else
	    	  Log.info("This page doesn't contain " + data);
			  DriverScript.bResult = false;
	}
		

	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 2 seconds");
			Thread.sleep(2000);
		 }catch(Exception e){
			 Log.error("Not able to Wait due to " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}

	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the browser");
			Thread.sleep(1000);
			driver.close();
		 }catch(Exception e){
			 Log.error("Not able to Close the Browser due to " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}

	}