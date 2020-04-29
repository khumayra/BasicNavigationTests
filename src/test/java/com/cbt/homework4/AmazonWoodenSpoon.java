package com.cbt.homework4;
import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.BrowserUtil;
import org.openqa.selenium.*;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class AmazonWoodenSpoon {
    private WebDriver driver;
    private String URL = "https://www.amazon.com";
    private By firstItemBy = By.xpath("//i[@aria-label='Amazon Prime']/../../../../../..//h2");
    private By onlyPrimeBy = By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2) [1]");
    private By primeSign = By.xpath("//li[@aria-label='Prime Eligible']");
    private By clickBrandBy = By.xpath("(//span[@class='a-size-base a-color-base'])[38]");
    private By itemAfterClickingBrandBy = By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2) [1]");
    private By under25By = By.linkText("Under $25");
    private By allPricesBy =By.className("a-price-whole");
    /**
     * CART
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click search
     * 4. remember the name and the price of a random result
     * 5. click on that random result
     * 6. verify default quantity of items is 1
     * 7. verify that the name and the price is the same as the one from step 5
     * 8. verify button "Add to Cart" is visible
     */
    @Test
    public void cart() {
        List<WebElement> list = driver.findElements(By.cssSelector("[class='a-size-base-plus a-color-base a-text-normal"));
        List<WebElement> whole = driver.findElements(By.cssSelector(".a-price-whole"));
        List<WebElement> fractionPrice = driver.findElements((By.cssSelector(".a-price-fraction")));
        Random r = new Random();
        int index = r.nextInt(list.size());
        System.out.println(index);
        System.out.println(list.size());
        String item = list.get(index).getText().trim();
        String price = "$" + whole.get(index).getText() + "." + fractionPrice.get(index).getText();
        list.get(index).click();
        String actual = driver.findElement(By.id("productTitle")).getText().trim();
        assertEquals(actual, item);
        String actualPrice = driver.findElement(By.id("priceblock_ourprice")).getText();
        assertEquals(actualPrice, price);
        String qty = driver.findElement(By.cssSelector("span[class='a-dropdown-label']+span")).getText().trim();
        assertEquals(qty, "1");
        assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());
    }
    /**
     * PRIME
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click search
     * 4. remember name first result that has prime label
     * 5. select Prime checkbox on the left
     * 6. verify that name first result that has prime label is same as step 4
     * 7. check the last checkbox under Brand on the left
     * 8. verify that name first result that has prime label is different
     */
    @Test
    public void verifyPrimeAmazonItem() {
        String firstResult = driver.findElement(firstItemBy).getText().trim();
        System.out.println("firstResult = " + firstResult);
        driver.findElement(primeSign).click();
        BrowserUtil.wait(10);
        String afterClickFirstItem = driver.findElement(onlyPrimeBy).getText().trim();
        System.out.println("afterClickFirstItem = " + afterClickFirstItem);
        assertEquals(firstResult, afterClickFirstItem);
        driver.findElement(clickBrandBy).click();
        String itemAfterClickingBrand = driver.findElement(itemAfterClickingBrandBy).getText().trim();
        System.out.println("itemAfterClickingBrand = " + itemAfterClickingBrand);
        assertNotEquals(firstResult, itemAfterClickingBrand);
    }
    /**
     * MORE SPOONS
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. remember all Brand names on the left
     * 4. select Prime checkbox on the left
     * 5. verify that same Brand names are still displayed
     */
    @Test
    public void moreSpoons(){
        List<WebElement> allBrandNames=driver.findElements(By.xpath("//div[@id='p_89-title']/following-sibling::ul"));
        for (WebElement allBrandName : allBrandNames) {
            System.out.println("allBrandName = " + allBrandName.getText().trim());
        }
        driver.findElement(primeSign).click();
        BrowserUtil.wait(10);
        List<WebElement> afterClickingPrimeAllBrandNames=driver.findElements(By.xpath("//div[@id='p_89-title']/following-sibling::ul"));
        for (WebElement afterClickingPrimeAllBrandName : afterClickingPrimeAllBrandNames) {
            System.out.println("afterClickingPrimeAllBrandName = " + afterClickingPrimeAllBrandName.getText().trim());
        }
        assertEquals(allBrandNames,afterClickingPrimeAllBrandNames);
    }
    /**
     * CHEAP SPOONS
     * 1.go to https://amazon.com
     * 2.search for "wooden spoon"
     * 3.click on Price option Under $25 on the left
     * 4.verify that all results are cheaper than $25
     */
    @Test
    public void under$25(){
        driver.findElement(under25By).click();
        List<WebElement> allPrices = driver.findElements(allPricesBy);
        List<String> allPricesText=BrowserUtil.getTextFromWebElements(allPrices);
        System.out.println("allPricesText = " + allPricesText);
        for (String eachPrice : allPricesText) {
            int price=Integer.parseInt(eachPrice);
            assertTrue(price<=25);
        }
    }
    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        BrowserUtil.wait(10);
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
