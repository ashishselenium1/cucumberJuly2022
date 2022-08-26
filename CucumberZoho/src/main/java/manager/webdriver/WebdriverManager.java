package manager.webdriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class WebdriverManager extends ValidationKeywords{
	
	public WebdriverManager() {
		System.out.println("WebDriver manager Init");
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
		prop = new Properties();
		FileInputStream fs;
		try {
			fs = new FileInputStream(path);
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.get("login_url"));
		softAssert = new SoftAssert();
	}
	
	
	public void selectDate(String date) {
		
	}

	public void searchLead(String leadName) {
		int rowNum = getRowNumWithCellData("leads_table_xpath", leadName);
		if(rowNum==-1)
			logFailure("Lead is not present", true);
		log("Lead "+leadName+" found on row number "+rowNum);
		System.out.println("Row num "+rowNum);
		cacheTable.put("rownumber", String.valueOf(rowNum));
		cacheTable.put("leadName", leadName);
	}


	public void deleteLead() {
		System.out.println(cacheTable.get("rownumber"));
		log("Deleting lead "+cacheTable.get("leadName"));
		driver.findElement(By.xpath("//table[@role='grid']/tbody/tr["+cacheTable.get("rownumber")+"]/td[1]/label/span")).click();
		click("bulk_action_dropdown_xpath");
		click("delete_option_xpath");
		click("proceed_button_xpath");
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
}
