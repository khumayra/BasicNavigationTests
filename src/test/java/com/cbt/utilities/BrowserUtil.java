package com.cbt.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrowserUtil {
   public static void wait(int seconds){
           try{
               Thread.sleep(seconds*1000);
           } catch (InterruptedException e){
               e.printStackTrace();
           }
   }

    public static List<String> getTextFromWebElements(List<WebElement> elements){
        List<String> textValues =new ArrayList<>();
        for (WebElement element: elements){
            if(!element.getText().isEmpty()){
                textValues.add(element.getText());
            }
        }
        return textValues;
    }


    public static String takeScreenShot(String name){
        String path = "";
       if (System.getProperty("os.name").equalsIgnoreCase("mac")) {
           path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
       } else {
           path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
       }
        System.out.println("OS name: " + System.getProperty("os.name"));
        System.out.println("Screenshot is here: " + path);

        TakesScreenshot screenshot = (TakesScreenshot) BrowserFactory.getDriver();
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File (path);

        try{
            FileUtils.copyFile(source, file );
        } catch (IOException e){
            e.printStackTrace();
        }

        return path;
    }
}
