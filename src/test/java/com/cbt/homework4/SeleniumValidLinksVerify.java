package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.apache.http.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumValidLinksVerify {
    private WebDriver driver;

    @Test
    public void linksVerify(){
        List<WebElement> list = driver.findElements(By.tagName("a"));
        String url = "";
        Iterator<WebElement> iterator = list.iterator();
        boolean isValid = true;
        while (iterator.hasNext()){
            try {
                url =iterator.next().getAttribute("href");
                //we create url object that need to interact with HttpURLConnection
              URL urlConnection = new URL(url);
              HttpURLConnection http = (HttpURLConnection) urlConnection.openConnection();
              http.setConnectTimeout(3000);
              http.connect();
              Assert.assertTrue(http.getResponseCode()== 200);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        driver.get("https://www.selenium.dev/documentation/en/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

}
