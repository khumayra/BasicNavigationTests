package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {


    public static WebDriver getDriver (String driverName) {
        // you are using Windows, and if  the String argument is `safari`, return null.
        if (System.getProperty("os.name").contains("Windows") &&
             driverName.equalsIgnoreCase("safari")||
            System.getProperty("os.name").contains("mac")&&driverName.equalsIgnoreCase("edge")) {
            return null;
        }
            /*4. In all other cases, return a new WebDriver object
            for   ChromeDriver,FirefoxDriver, EdgeDriver or
            SafariDriver b ased on the value of the stringargument.
            NOTEFor this exercise, you need to research how to launch
            Edge using Selenium if you are using Windows.
            NOTEFor this exercise, you need to research how to launch Safari
            using Selenium   if you are using Mac.*/
        if (driverName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().version("79.0").setup();
            return new ChromeDriver();
        } else if (driverName.equalsIgnoreCase("firefoxdriver")){
            WebDriverManager.firefoxdriver().setup();
            return  new FirefoxDriver();
        } else if (driverName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } else {
            return new SafariDriver();
        }

    }
}
