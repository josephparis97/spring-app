


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src//test//java//resources//features"},plugin = {"pretty", "json:target/cucumber.json"})
public class ModificationAdresseTest {

	
	
}
