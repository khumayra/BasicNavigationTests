package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonPracticeTest {
    private By allBy = By.cssSelector("span[class='nav-search-label']");
    private By departmBy = By.xpath("//option[@selected='selected']/following-sibling::option");
    private WebDriver driver;

    public static List<String> saveAsStringList (List<WebElement> list){
        List<String> result = new ArrayList<>();
        for (WebElement each : list) {
            result.add(each.getText());
        }
        return result;
    }
    @Test
    public void allBtnVerify(){
        String actual = driver.findElement(allBy).getText();
        List<WebElement> departments = driver.findElements(departmBy);

        boolean sorted = true;
        String previous = "Audible Books & Originals";
        for ( WebElement each: departments) {
            String current = each.getText().trim();

            System.out.println("each "+ current);
            System.out.println("previous = " + previous);
            System.out.println("current.compareTo(previous) = " + current.compareTo(previous));

            if (current.compareTo(previous) < 0) { //cat //blue -1
                sorted = false;
                break;
            }
            previous = current;
        }
        Assert.assertEquals(actual, "All" );
        Assert.assertFalse(sorted);
    }
    @Test
    public void containsInDirectory(){
        driver.navigate().to("https://www.amazon.com/gp/site-directory");
        List <String> allDirectories = saveAsStringList(driver.findElements(By.tagName("h2")));
        List<String> allList = saveAsStringList(driver.findElements(departmBy));
        int count = 0;
        boolean isContains = false;
//NEED TO FINISH
        for (int i = 0; i <allDirectories.size() ; i++) {
            for (int j = 0; j <allList.size() ; j++) {
                if(allDirectories.get(i).contains(allList.get(j))){
                    isContains=true;
                    count++;
                    //System.out.println(count);
                }
            }
        }
        Assert.assertTrue(isContains);
    }
    @BeforeMethod
    public void setup(){
        driver  = BrowserFactory.getDriver();
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void teardown(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
