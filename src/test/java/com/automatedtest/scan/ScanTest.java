package com.automatedtest.scan;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com/automatedtest/scan/Scan.feature"},
        plugin = {"pretty",
        "json:target/cucumber_json_reports/scan.json",
        "html:target/scan-result.html"},
        glue = {"com.automatedtest.scan.infrastructure.driver",
                "com.automatedtest.scan.homepage"})
public class ScanTest {
}
