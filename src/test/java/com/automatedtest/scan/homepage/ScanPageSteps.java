package com.automatedtest.scan.homepage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.logging.LogEntry;

import java.io.IOException;
import java.util.List;

public class ScanPageSteps {

    private ScanPage scanPage;

    public ScanPageSteps() {
        this.scanPage = new ScanPage();
    }

    @Given("^A user navigates to page \"([^\"]*)\"$")
    public void aUserNavigatesToPage(String url) {
        this.scanPage.goToPage(url);
    }

    @Then("There are no console errors$")
    public void pageConsoleOk() {
        List<LogEntry> logs = this.scanPage.getConsoleErrors();
        if (logs != null)
            Assert.assertTrue("Console errors found: " + logs, logs.isEmpty());
    }

    @Then("The response code is OK$")
    public void pageResponseOk() throws IOException {
        String currentUrl = scanPage.getUrl();
        int response = this.scanPage.getPageResponseCode(currentUrl);
        Assert.assertTrue("Http response code: " + response,
                response == 200);
    }

    @Then("All links work$")
    public void pageLinksOk() throws IOException {
        List<String> links = this.scanPage.getPageLinks();
        for (String link : links) {
            int response = this.scanPage.getPageResponseCode(link);
            Assert.assertTrue(link + " is a broken link",
                    response < 400);
        }
    }
}
