package seng202.team3;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/features",
		 glue="seng202.team3.stepdefs",
         plugin={"pretty"},
         snippets = SnippetType.CAMELCASE)
		 
public class RunCucumberTest {
}