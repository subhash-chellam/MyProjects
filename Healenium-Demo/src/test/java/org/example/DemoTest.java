package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.epam.healenium.SelfHealingDriver;

import com.epam.healenium.SelfHealingDriver;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import org.openqa.selenium.support.ui.WebDriverWait;

class TestBase {
    public static SelfHealingDriver driver;
    private static WebDriver delegate;

    public static void main(String[] args) throws MalformedURLException {
    //    WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        System.setProperty("hlm.url", "http://98.81.252.88:7878");
        // Local ChromeDriver as delegate
        WebDriver delegate =  new RemoteWebDriver(new URL("http://98.81.252.88:8085"), options);

        // Wrap ChromeDriver with Healenium SelfHealingDriver
        SelfHealingDriver driver = SelfHealingDriver.create(delegate);

        // Use Healenium driver as normal Selenium WebDriver
        driver.get("https://google.co.in");
      //  System.out.println("Page title: " + driver.getTitle());
        try {
            // Use a valid locator to avoid null in healing engine
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Healenium SelfHealingDriver");
            searchBox.sendKeys(Keys.ENTER);

            System.out.println("Page title after search: " + driver.getTitle());
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        }

        // Cleanup
        driver.quit();

    }
}
