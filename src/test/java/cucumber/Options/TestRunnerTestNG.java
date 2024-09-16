package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features/VideoBookingDolbyValidation.feature", // Path to your feature files
    glue = {"stepDefinition", "CustomParameterTypes"}, // Package(s) containing step definitions
    plugin = {"pretty", "html:target/cucumber-reports"}, // Reporting plugins
    monochrome = true, // Makes the output more readable,
    tags = " @CreateEncoder"
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {
    // No need to add code here
}
