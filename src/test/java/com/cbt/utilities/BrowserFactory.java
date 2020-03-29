package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {


    public static WebDriver getDriver () {
        // you are using Windows, and if  the String argument is `safari`, return null.
        String browser = ConfigurationReader.getProperty("browser");
        if (System.getProperty("os.name").contains("Windows") &&
             browser.equalsIgnoreCase("safari")||
            System.getProperty("os.name").contains("mac")&&browser.equalsIgnoreCase("edge")) {
            return null;
        }
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().version("79.0").setup();
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefoxdriver")){
            WebDriverManager.firefoxdriver().setup();
            return  new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } else {
            return new SafariDriver();
        }

    }
}
