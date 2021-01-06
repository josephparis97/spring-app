
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/java/resources/features"},plugin = {"pretty", "json:target/cucumber.json"},glue = "stepdefinitions")
public class ModificationAdresseTest extends AbstractTestNGCucumberTests{

	
}
