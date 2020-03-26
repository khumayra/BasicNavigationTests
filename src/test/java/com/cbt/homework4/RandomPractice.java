package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomPractice {
    private WebDriver driver;
    private String URL = "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox";

    @Test
    public void checkBoxTest(){
        int countFriday = 0;
        Random r = new Random();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        List<WebElement> labels = driver.findElements(By.xpath("//label"));

        while (countFriday!=3){
            int index = r.nextInt(7);
            WebElement current = checkboxes.get(index);

            if(current.isEnabled()){
                current.click(); // to check
                System.out.println(labels.get(index).getText());
                current.click(); // to uncheck
                if (labels.get(index).getText().equals("Friday")){
                    countFriday++;
                    System.out.println(countFriday);
                }
            }
        }
        Assert.assertEquals(countFriday,3);
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
