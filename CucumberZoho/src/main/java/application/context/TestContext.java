package application.context;

import com.aventstack.extentreports.ExtentReports;

import manager.pageobjects.PageObjectManager;
import manager.webdriver.WebdriverManager;

public class TestContext {
	
	PageObjectManager pageObjectManager;
	
	public TestContext() {
		System.out.println("Context init");
		pageObjectManager = new PageObjectManager();
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	public void startScenario(String scenarioName) {
		pageObjectManager.getWebDriverManager().initReports(scenarioName);
	}
	
	public void endScenario() {
		pageObjectManager.getWebDriverManager().quit();
	}
	
	public void log(String message) {
		pageObjectManager.getWebDriverManager().log(message);
	}

	public void logFailure(String message,boolean isCritical) {
		pageObjectManager.getWebDriverManager().logFailure(message, isCritical);
		
	}
	

	// buy stock, sell stock, verification of stocks
	
	
	// Extent reports
	// More scenarios
	// GRID and Jenkins

}
