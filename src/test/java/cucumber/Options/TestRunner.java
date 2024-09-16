package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features/VideoBookingNoneValidation.feature",
    glue = {"stepDefinition","CustomParameterTypes"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true,
    publish = true
)
public class TestRunner {
	
	
}
