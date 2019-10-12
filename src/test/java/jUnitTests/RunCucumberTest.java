package jUnitTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"},
        snippets = SnippetType.CAMELCASE,
        glue = "step_definitions",
        features = "src/test/java/features")


public class RunCucumberTest {

}
