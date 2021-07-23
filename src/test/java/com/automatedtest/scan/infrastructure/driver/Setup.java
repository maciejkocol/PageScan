package com.automatedtest.scan.infrastructure.driver;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Setup {

    public static WebDriver driver;

    @Before
    public void setWebDriver() {

        String browser = System.getProperty("browser");

        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("window-size=1024,768");
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver",
                        WebDriverManager.chromedriver().getDownloadedDriverPath());
                System.setProperty("webdriver.chrome.args", "--disable-logging");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.addArguments("disable-infobars");
                firefoxOptions.addArguments("--disable-extensions");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.gecko.driver",
                        WebDriverManager.firefoxdriver().getDownloadedDriverPath());
                System.setProperty("webdriver.gecko.args", "--disable-logging");
                System.setProperty("webdriver.gecko.silentOutput", "true");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }
}
