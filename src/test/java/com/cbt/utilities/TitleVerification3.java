package com.cbt.utilities;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification3 {
    static WebDriver driver;
    public static void main(String[] args) throws Exception{
        List<String> urls = Arrays.asList("https://luluandgeorgia.com",
                "https://wayfair.com/",
                "https://walmart.com", "https://westelm.com/");


        //In case if only one hyperlink contains only part of the title
        for (String each:urls) {
            driver = BrowserFactory.getDriver();
            driver.get(each);

            String t = driver.getTitle();
            if (each.contains(t.substring(0,t.indexOf(" ")).toLowerCase())){
                System.out.println("The title is the part of the url");
                System.out.println();
            } else {
                System.out.println("The title is NOT the part of the url");
            }
            Thread.sleep(2000);
            driver.quit();
        }

       /* In case if we comparing with the whole title name
       for (String each : urls) {
            driver.get(each);
            if(each.contains(driver.getTitle().replace(" ","").toLowerCase())){
                System.out.println("Title is the part of URL!");
            } else {
                System.out.println("Title is not the part of URL!");
            }

        }*/


    }

}
