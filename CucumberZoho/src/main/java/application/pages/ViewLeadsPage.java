package application.pages;

import manager.webdriver.WebdriverManager;

public class ViewLeadsPage {
	
	WebdriverManager webDriverManager;
	
	public ViewLeadsPage(WebdriverManager webDriverManager) {
		this.webDriverManager=webDriverManager;
	}

	public void searchLead(String leadName) {
		webDriverManager.searchLead(leadName);
		
	}

	public void deleteLead() {
		webDriverManager.deleteLead();
		
	}

	public void checkLeadDeletionStatus() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rNum = webDriverManager.getRowNumWithCellData("leads_table_xpath", webDriverManager.getCacheTableValue("leadName"));
		webDriverManager.log("Lead row number is "+rNum);
		if(rNum!=-1)
			webDriverManager.logFailure("Lead not deleted Successfully", true);
		else {
			//log a pass
		}
	}
	
	

}
