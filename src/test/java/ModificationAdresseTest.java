

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.server.SpringAppApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src//test//java//resources//features"},plugin = {"pretty", "json:target/cucumber.json"})
public class ModificationAdresseTest {

	
	
}
