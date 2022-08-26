package manager.webdriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

// click
// type
// select
// read the text
// open a browser
// type a url
public class GenericKeywords {
	WebDriver driver;
	Properties prop;
	ExtentReports rep ;
	ExtentTest test;
	SoftAssert softAssert;
	Hashtable<String, String> cacheTable = new Hashtable<String, String>();
	public void openBrowser(String browser) {
		log("Opening browser "+browser);
		if(browser.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "D:\\Ashish\\driverexes\\exes\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Ashish\\driverexes\\exes\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "D:\\Ashish\\driverexes\\exes\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));// presence
		
	}
	
	public void navigate(String urlKey) {
		driver.get(getProperty(urlKey));
	}
	
	public void click(String locatorKey) {
		getElement(locatorKey).click();
	}
	
	public void type(String locatorKey, String data) {
		getElement(locatorKey).sendKeys(data);
	}
	
	public void select(String locatorKey, String data) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement e = getElement(locatorKey);
		Select s = new Select(e);
		s.selectByVisibleText(data);
	}
	
	public String getText(String locatorKey) {
		return getElement(locatorKey).getText();
	}
	public void clear(String locatorKey) {
		getElement(locatorKey).clear();
	}
	
	public int getRowNumWithCellData(String rowsLocator, String data) {
		List<WebElement> rows = driver.findElements(getLocator(rowsLocator));
		for(int rNum=0;rNum<rows.size();rNum++) {
			WebElement row = rows.get(rNum);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(int cNum=0;cNum<cells.size();cNum++) {
				if(cells.get(cNum).getText().equals(data)) {
					return (rNum+1);
				}
			}
		}
		// data not found
		return -1;
	}
	
	public void moveMouseOverElement(String locatorKey) {
		WebElement e= getElement(locatorKey);
		Actions act = new Actions(driver);
		act.moveToElement(e).build().perform();
	}
	
	public void waitForPageToCompletelyLoad() {		
		// page loading status in JS
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String state = (String)js.executeScript("return document.readyState");
		while(!state.equals("complete")) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 state = (String)js.executeScript("return document.readyState");
		}
	}
	
	// check the presence and visibile
	public boolean isElementPresent(String locatorKey) {
		System.out.println(locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until((ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/***************************Factory methods******************************/
	
	public WebElement getElement(String locatorKey) {
		// check the presence and visibility of the element
		if(!isElementPresent(locatorKey)) {
			// report a critical failure failure and stop the test
			logFailure("Element not found "+locatorKey, true);
			System.out.println("Element not found");
		}
		// extract and return
		WebElement e = driver.findElement(getLocator(locatorKey));
		return e;
	}
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	// decide the locator str
	public By getLocator(String locatorKey) {
		By ref = null;
		if(locatorKey.endsWith("_id"))
			ref=By.id(getProperty(locatorKey));
		else if(locatorKey.endsWith("_xpath"))
			ref=By.xpath(getProperty(locatorKey));
		else if(locatorKey.endsWith("_css"))
			ref=By.cssSelector(getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			ref=By.name(getProperty(locatorKey));
		return ref;
	}
	
	public void initReports(String scenarioName) {
		 rep = ExtentManager.getReport(System.getProperty("user.dir")+"//reports//");
		 test = rep.createTest(scenarioName);
		 log( "starting scenario " + scenarioName);
	}
	
	public void quit() {
		if(driver!=null)
			driver.quit();
		if(rep!=null)
		rep.flush();
	}
	
	public void log(String message) {
		// message in the reports
		test.log(Status.INFO, message);
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}
	
	public void logFailure(String failureMessage, boolean isCriticalFailure) {
		// extent reports
		test.log(Status.FAIL, failureMessage);
		// screenshot
		// takeScreenshot();
		// cucumber
		softAssert.fail(failureMessage);
		if(isCriticalFailure)
			softAssert.assertAll();
	}
	
	public void takeScreenshot() {
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//put the screenshot in the file
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
			//embed screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCacheTableValue(String key) {
		return cacheTable.get(key);
	}
    

}
// parallel execution
// jenkins and grid
