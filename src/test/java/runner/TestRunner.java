package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resource/Feature",
        glue={"stepDefinations"},
        plugin={"pretty","html:target/cucumber-reports","Utility.ExtentReportListners"})
public class TestRunner {
}
