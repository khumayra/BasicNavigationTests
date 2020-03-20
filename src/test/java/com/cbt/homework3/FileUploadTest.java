package com.cbt.homework3;

import com.cbt.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FileUploadTest {
    private RemoteWebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com";
    private By fileUploadLinkBy = By.linkText("File Upload");
    private By chooseFileBtnBy = By.id("file-upload");
    private By uploadFileBtnBy = By.id("file-submit");
    private By successMsgBy = By.tagName("h3");
    private By autoCompleteLinkBy = By.linkText("Autocomplete");
    private By countryTextBoxBy = By.id("myCountry");
    private By submitBtnBy = By.xpath("//input[@type='button']");
    private By resultBy = By.id("result");
    private By dropDownCountryBy = By.id("myCountryautocomplete-list");


    @Test
    public void test7(){
        driver.findElement(fileUploadLinkBy).click();
        driver.findElement(chooseFileBtnBy).sendKeys("/Users/Khumayra/Desktop/HelloWorld.txt");
        driver.findElement(uploadFileBtnBy).click();
        BrowserUtil.wait(5);
        String actual = driver.findElement(successMsgBy).getText();
        String expected = "File Uploaded!";
        assertEquals(actual,expected);
    }

    @Test
    public void test8(){
        driver.findElement(autoCompleteLinkBy).click();
        BrowserUtil.wait(3);
        driver.findElement(countryTextBoxBy).sendKeys("United States");
        BrowserUtil.wait(3);
        driver.findElement(dropDownCountryBy).click();
        driver.findElement(submitBtnBy).click();
        String autoCompleteExpected = "You selected: United States of America";
        String autoCompleteActual = driver.findElement(resultBy).getText();
        assertEquals(autoCompleteActual,autoCompleteExpected);
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtil.wait(3);
    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
