package runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepDef",
        tags = "@register",
        plugin = {"pretty", "html:target/RegressionForm.html"}
)
public class RunTest {
}
