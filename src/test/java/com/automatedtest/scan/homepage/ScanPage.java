package com.automatedtest.scan.homepage;

import com.automatedtest.scan.basepage.BasePage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Iterator;

public class ScanPage extends BasePage{

    @FindBy(tagName = "a")
    private List<WebElement> linkElements;

    ScanPage() {
        PageFactory.initElements(driver, this);
    }

    void goToPage(String url){
        driver.get(url);
        wait.forLoad(10);
    }

    String getUrl(){
        return driver.getCurrentUrl();
    }


    List <LogEntry> getConsoleErrors() {
        try {
            LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
            // Retrieving logs
            List <LogEntry> logs = entry.getAll();
            return logs;
        } catch (UnsupportedCommandException e) {
            System.err.println("Could not read from browser console.");
        }

        return null;
    }

    List <String> getPageLinks() {
        List<String> links = new ArrayList<>();
        String url = "";
        Iterator<WebElement> it = linkElements.iterator();

        while (it.hasNext()) {
            url = it.next().getAttribute("href");

            if (url == null || url.isEmpty() || url.startsWith("mailto:")) {
                System.out.println(it.next().getText() + " link at " +
                        it.next().getLocation() + " is not configured correctly: " + url);
                continue;
            }

            links.add(url);
        }

        return links;
    }

    int getPageResponseCode(String url) throws IOException {
        HttpURLConnection huc = null;
        int response = 0;

        try {
            huc = (HttpURLConnection) (new URL(url).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();

            response = huc.getResponseCode();
            System.out.println("url: " + url + " response: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
