


import org.junit.runner.RunWith;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src//test//java//resources//features"},plugin = {"pretty", "json:target/cucumber.json"})
public class ModificationAdresseTest extends AbstractTestNGCucumberTests{

	
	
}
