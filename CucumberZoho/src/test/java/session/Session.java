package session;

import application.context.TestContext;
import application.pages.LaunchPage;
import application.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class Session {
	TestContext context;
	LaunchPage launchPage;
	LoginPage loginPage;
	public Session(TestContext context) {
		System.out.println("Session init ");
		this.context=context;
		launchPage = context.getPageObjectManager().getLaunchPage();
		loginPage = context.getPageObjectManager().getLoginPage();
	}	
	@Before
	public void init(Scenario scenario) {
		context.startScenario(scenario.getName());
	}
	
	@After
	public void quit(Scenario scenario) {
		context.endScenario();
	}
	
	
	@Given("I login into application using {string}")
	public void login(String browser) {
		launchPage.openBrowser(browser);
		launchPage.defaultLogin();
	}

}
