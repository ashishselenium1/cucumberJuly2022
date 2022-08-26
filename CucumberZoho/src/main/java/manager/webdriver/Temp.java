package manager.webdriver;

public class Temp {

	public static void main(String[] args) throws InterruptedException {
		WebdriverManager keywords = new WebdriverManager();
		keywords.openBrowser("Chrome");
		keywords.navigate("login_url");
		//keywords.validateTitle("login_user_email_id");
		keywords.type("login_user_email_id", "ashishthakur1983");
		keywords.type("login_user_pass_xpath", "pass@1234");
		keywords.validateElementPresent("login_button_css");
		keywords.click("login_button_css");
		keywords.select("portfolio_selection_id", "xxyyzz");
		//keywords.buyStock();
	}

}
