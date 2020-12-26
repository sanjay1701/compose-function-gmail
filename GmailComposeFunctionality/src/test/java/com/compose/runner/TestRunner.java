package com.compose.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features",glue="com.composefunction.stepdef",
monochrome=true,dryRun=false,
plugin = {"pretty","json:target/cucumber-reports/Cucumber.json",
		"junit:target/cucumber-reports/Cucumber.xml",
"html:target/cucumber-reports"})
public class TestRunner {
}
