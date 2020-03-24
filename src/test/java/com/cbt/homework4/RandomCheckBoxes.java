package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomCheckBoxes {
    private WebDriver driver;
    private String URL = "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox";
    private Random random;

    @Test(description = "Generate random and select checkboxes ")
    public void clickCheckBoxes(){
        random = new Random();
        int fridayCount = 0;
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        List<WebElement> titles = driver.findElements(By.xpath("//label"));
        int r = 0;
        while (fridayCount!=3){
            r = random.nextInt(7);
            WebElement current =checkBoxes.get(r);
            if(current.isEnabled()){
                current.click();
                System.out.println(titles.get(r).getText());

                if (titles.get(r).getText().equals("Friday")) {
                    fridayCount++;
                }
                current.click();
            } else{
                continue;
            }
        }
        Assert.assertEquals(fridayCount,3);
    }
    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
