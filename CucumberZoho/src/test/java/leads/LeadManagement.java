package leads;

import application.context.TestContext;
import application.pages.CreateLeadPage;
import application.pages.LandingPage;
import application.pages.ViewLeadsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LeadManagement {
	TestContext context;
	LandingPage landingPage;
	CreateLeadPage createLeadPage;
	ViewLeadsPage viewLeadsPage;
	
	public LeadManagement(TestContext context) {
		System.out.println("Session init ");
		this.context=context;
		landingPage = context.getPageObjectManager().getLandingPage();
		createLeadPage = context.getPageObjectManager().getCreateLeadPage();
		viewLeadsPage = context.getPageObjectManager().getViewLeadsPage();
	}	

    @When("I go to {string} lead page")
    public void gotoCreateLead(String action) {
    	if(action.equals("create"))
    		landingPage.gotoCreateLeadPage();
    	else if(action.equals("view"))
    		landingPage.gotoViewLeadPage();
    }
    
    @And("I create following lead")
    public void createLead(DataTable dataTable) {
    	createLeadPage.createLead(dataTable.asList());
    }
    
    @Then("Lead should be created")
    public void validateLeadCreation() {
    	// you
    	context.log("Created Lead successfully");
    }
    
    @Then("Lead {string} should be present")
    public void searchLead(String leadName) {
    	viewLeadsPage.searchLead(leadName);
    }
    
    @When("I delete the lead")
    public void deleteLead() {
    	viewLeadsPage.deleteLead();
    }
    
    @Then("Lead should be deleted successfully")
    public void checkLeadDeletionStatus() {
    	viewLeadsPage.checkLeadDeletionStatus();
    }
    

    


}
