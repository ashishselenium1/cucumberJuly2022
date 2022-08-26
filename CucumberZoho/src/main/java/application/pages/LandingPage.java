package application.pages;

import manager.webdriver.WebdriverManager;

public class LandingPage {
	WebdriverManager webDriverManager;
	
	public LandingPage(WebdriverManager webDriverManager) {
		this.webDriverManager=webDriverManager;
	}
	
	public void gotoCreateLeadPage() {
		webDriverManager.moveMouseOverElement("leads_tab_xpath");
		webDriverManager.click("create_lead_link_xpath");
	}

	public void gotoViewLeadPage() {
		webDriverManager.moveMouseOverElement("leads_tab_xpath");
		webDriverManager.click("view_leads_xpath");

		
	}
}
