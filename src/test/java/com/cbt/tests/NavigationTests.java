package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.WebDriver;

public class NavigationTests {
 static WebDriver driver;

    public static void main(String[] args) throws Exception {
           // chromeTest();
            safariTest();
    }

    public static void chromeTest() throws Exception{
        driver = BrowserFactory.getDriver();
        driver.get("https://google.com");
        String title1 = driver.getTitle();

        driver.navigate().to("https://etsy.com");
        String title2 = driver.getTitle();

        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title1);

        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title2);

        Thread.sleep(2000);
        driver.quit();
    }
    public static void fireFox() throws Exception{
        Thread.sleep(2000);
        driver.quit();
    }
    public static void safariTest() throws Exception{
        driver = BrowserFactory.getDriver();
        driver.get("https://google.com");
        String title1 = driver.getTitle();

        driver.navigate().to("https://etsy.com");
        String title2 = driver.getTitle();

        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title1);

        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title2);

        Thread.sleep(2000);
        driver.quit();

    }
}

