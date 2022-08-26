package application.pages;

import java.util.List;

import manager.webdriver.WebdriverManager;

public class CreateLeadPage {

	WebdriverManager webDriverManager;
	
	public CreateLeadPage(WebdriverManager webDriverManager) {
		this.webDriverManager=webDriverManager;
	}
	
	public void createLead(List<String> leadDetails) {
		webDriverManager.log("Creating lead with details "+leadDetails.toString());
		webDriverManager.type("first_name_xpath", leadDetails.get(0));
		webDriverManager.type("last_name_xpath",  leadDetails.get(1));
		webDriverManager.type("office_phone_xpath",  leadDetails.get(2));
		webDriverManager.click("lead_save_button_xpath");
		
	}
}
