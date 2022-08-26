package manager.pageobjects;

import application.pages.CreateLeadPage;
import application.pages.LandingPage;
import application.pages.LaunchPage;
import application.pages.LoginPage;
import application.pages.ViewLeadsPage;
import manager.webdriver.WebdriverManager;

public class PageObjectManager {
	
	WebdriverManager webdriverManager;
	LaunchPage launchPage;
	LoginPage loginPage;
	LandingPage landingPage;
	CreateLeadPage createLeadPage;
	ViewLeadsPage viewLeadsPage;
	
	public PageObjectManager() {
		System.out.println("Page Object Manager Init");
		webdriverManager = new WebdriverManager();
	}
	
	public WebdriverManager getWebDriverManager() {
		return webdriverManager;
	}

	public LaunchPage getLaunchPage() {
		if(launchPage==null)
			launchPage=new LaunchPage(webdriverManager);
		return launchPage;
	}
	
	public LoginPage getLoginPage() {
		if(loginPage==null)
			loginPage=new LoginPage(webdriverManager);
		return loginPage;
	}
	
	public LandingPage getLandingPage() {
		if(landingPage==null)
			landingPage=new LandingPage(webdriverManager);
		return landingPage;
	}
	
	public CreateLeadPage getCreateLeadPage() {
		if(createLeadPage==null)
			createLeadPage=new CreateLeadPage(webdriverManager);
		return createLeadPage;
	}
	
	public ViewLeadsPage getViewLeadsPage() {
		if(viewLeadsPage==null)
			viewLeadsPage = new ViewLeadsPage(webdriverManager);
		return viewLeadsPage;
	}
	
}
