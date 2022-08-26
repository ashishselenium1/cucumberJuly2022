package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/",
        glue = {"session","leads"},
        plugin = { "html:target/cucumber-reports.html","rerun:rerun/failed_scenarios.txt"},
        monochrome = true,
        dryRun = false,
        tags = "@DeleteLead"
        )

public class MyRunner extends AbstractTestNGCucumberTests {
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

// reports
// Multiple scenarios
// GRID and Jenkins

// Creating a lead
// Delete the lead
// Convert the lead



