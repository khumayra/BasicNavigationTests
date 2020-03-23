package com.cbt.homework3;

import com.cbt.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCodesTests {
    private WebDriver driver;
    private By statusBy = By.linkText("Status Codes");

    @DataProvider (name = "testData")
    public static Object [] testData(){
        return new Object [] {"200","301","404","500"};
    }

    @Test (dataProvider="testData")
    public void test9(String code){
        driver.findElement(statusBy).click();
        BrowserUtil.wait(2);
        driver.findElement(By.linkText(code)).click();
        BrowserUtil.wait(2);

        String actual9 = driver.findElement(By.tagName("p")).getText();
        String expect9 = "This page returned a "+code+" status code.";

        Assert.assertTrue(actual9.contains(expect9));
    }



    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtil.wait(3);
    }

    @AfterMethod
    public void tearDown(){
        if (driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
