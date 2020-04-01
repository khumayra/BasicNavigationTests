package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.BrowserUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    private By itemNameBy = By.xpath("//div[@class='a-section a-spacing-medium']//span[@class='a-size-base-plus a-color-base a-text-normal']");


    //private By primeLabelBy = By.xpath("//div[@class='a-section a-spacing-medium']//i[@aria-label='Amazon Prime']");

    @Test
    public void searchWoodenSpoonVerify() {
        List<WebElement> list = driver.findElements(itemNameBy);
        List<WebElement> wholePrice = driver.findElements(By.cssSelector(".a-price-whole"));
        List<WebElement> fractionPrice = driver.findElements(By.cssSelector(".a-price-fraction"));

        Random r = new Random();
        int index = r.nextInt(list.size());

        String item = list.get(index).getText().trim();
        String price = "$" + wholePrice.get(index).getText() + "." + fractionPrice.get(index).getText();

        list.get(index).click();

        String actualItem = driver.findElement(By.id("productTitle")).getText().trim();
        Assert.assertEquals(actualItem, item);

        String actualPrice = driver.findElement(By.id("priceblock_ourprice")).getText();
        Assert.assertEquals(actualPrice, price);

        String qty = driver.findElement(By.cssSelector("span[class='a-dropdown-label']+span")).getText().trim();
        Assert.assertEquals(qty, "1");

        Assert.assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());
    }

    @Test
    public void verifyPrimeAmazonItem(){
        /*Solution 1
        List<WebElement> allItems = driver.findElements(allItemsBy);
        String actualName = "";
        for (int i = 0; i <allItems.size() ; i++) {
            WebElement amazonItem = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-medium']//i[@aria-label='Amazon Prime'])"
                                                                +"["+(i+1)+"]"));

            if(amazonItem!=null){
                actualName = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-medium']//span[@class='a-size-base-plus a-color-base a-text-normal'])"
                                    +"["+(i+1)+"]")).getText();
                System.out.println("actualName = " + actualName);
                break;
            }
        }
        WebElement primeCheckBox =driver.findElement(By.xpath("//li[@aria-label='Prime Eligible']"));
        primeCheckBox.click();
        BrowserUtil.wait(5);
        String expectedName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText().trim();
        System.out.println("expectedName = " + expectedName);
        Assert.assertEquals(actualName,expectedName );*/

        //Solution 2
        String actualName = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]")).getText();
        driver.findElement(By.xpath("//li[@aria-label='Prime Eligible']")).click();
        BrowserUtil.wait(5);
        String expectedName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText().trim();
        Assert.assertEquals(actualName,expectedName );
        driver.findElement(By.xpath("//ul[@aria-labelledby='p_89-title']//li[last()]")).click();
        BrowserUtil.wait(5);
        String brandSelectedActual = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText().trim();
        Assert.assertFalse(brandSelectedActual.equalsIgnoreCase(expectedName));

    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(searchBy).sendKeys("wooden spoon");
        driver.findElement(searchBtnBy).click();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
