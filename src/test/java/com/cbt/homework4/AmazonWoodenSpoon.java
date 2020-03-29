package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.BrowserUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.channels.Selector;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AmazonWoodenSpoon {
    private WebDriver driver;
    private String URL = "https://www.amazon.com/";
    private By searchBy = By.id("twotabsearchtextbox");
    private By searchBtnBy = By.xpath("//input[@value='Go']");

    @Test
    public void searchWoodenSpoonVerify(){
        driver.findElement(searchBy).sendKeys("wooden spoon");
        driver.findElement(searchBtnBy).click();
        BrowserUtil.wait(5);
        JavascriptExecutor js = (JavascriptExecutor) driver;

//      String items = js.executeScript("document.getElementsByClassName('a-size-base-plus a-color-base a-text-normal')").toString();
//        System.out.println(items);
//        List<WebElement> prices = driver.findElements(By.className("a-offscreen"));
//
//        Random r = new Random();
//        int index = r.nextInt(list.size());
//        System.out.println(list.size());
//        System.out.println(index);
//
//        String item = list.get(index).getText().trim();
//        String itemPrice = "";
//            if (prices.get(index).getAttribute("data-a-strike").equals("true")){
//                itemPrice = prices.get(index-1).getText();
//            } else {
//                itemPrice = prices.get(index).getText();
//            }
//
//            list.get(index).click();
//           String actualItem= driver.findElement(By.id("productTitle")).getText().trim();
//           Assert.assertEquals(actualItem,item);
//
//           String actualPrice = driver.findElement(By.id("priceblock_ourprice")).getText();
//           Assert.assertEquals(actualPrice,itemPrice);
//
//           Select select = new Select(driver.findElement(By.cssSelector("span[class='a-dropdown-label']+span")));
//           String actualQty = select.getFirstSelectedOption().getText().trim();
//           Assert.assertEquals(actualQty,"1");
//
//           Assert.assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());



    }

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
