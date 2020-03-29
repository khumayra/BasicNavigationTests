package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SchoolComTask {
    private WebDriver driver;
    private String URL = "https://www.w3schools.com/";
    private By tagName_a_By = By.tagName("a");

    @Test
    public void getTextFromTagName_a() {
        List<WebElement> allLinks = driver.findElements(tagName_a_By);
        System.out.println(allLinks.size());
        for (WebElement allLink : allLinks) {
            if (allLink.isDisplayed()) {
                System.out.println(allLink.getText());
                if (!allLink.getAttribute("href").contains("void")) {
                    System.out.println(allLink.getAttribute("href"));
                }
            }
        }

    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

