package application.pages;

import manager.webdriver.WebdriverManager;

public class LaunchPage {
	WebdriverManager webDriverManager;
	
	public LaunchPage(WebdriverManager webDriverManager) {
		this.webDriverManager=webDriverManager;
	}
	
	public void openBrowser(String browser) {
		webDriverManager.openBrowser(browser);
		webDriverManager.navigate("login_url");
	}

	public void defaultLogin() {
		webDriverManager.type("username_xpath", webDriverManager.getProperty("username"));
		webDriverManager.type("password_xpath", webDriverManager.getProperty("password"));
		webDriverManager.click("login_button_id");
		webDriverManager.waitForPageToCompletelyLoad();
		
	}

}
