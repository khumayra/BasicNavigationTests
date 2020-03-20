package com.cbt.homework3;

import com.cbt.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SignUpFormTest {
    private WebDriver driver;
    private String emailURL = "https://www.tempmailaddress.com/";
    private String URL = "https://practice-cybertekschool.herokuapp.com/";
    private By emailBy = By.id("email");
    private static String email;
    private By signUpBy = By.linkText("Sign Up For Mailing List");
    private By fullNameBy = By.name("full_name");
    private String name = "Tom Smith";
    private By userEmailBy = By.name("email");
    private By successMsg = By.name("signup_message");
    private By verificationEmailBy = By.cssSelector("td[class='from']");
    private By replyEmailBy = By.cssSelector("span[id='odesilatel']");
    private By replySubjectBy = By.cssSelector("span[id='predmet']");
    private static WebElement currentEmail;

    @Test
    public void test6(){
        driver.findElement(signUpBy).click();
        driver.findElement(fullNameBy).sendKeys(name);
        driver.findElement(userEmailBy).sendKeys(email, Keys.ENTER);
        BrowserUtil.wait(3);
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        String actual = driver.findElement(successMsg).getText();
        assertEquals(actual,expected);
        driver.navigate().to(emailURL);
        BrowserUtil.wait(5);

        boolean actualResult = false;

        List<WebElement> emails = driver.findElements(verificationEmailBy);
        String replyEmailExpected = "do-not-reply@practice.cybertekschool.com";
        for (WebElement email: emails) {
            if (email.getText().trim().equals(replyEmailExpected)){
                actualResult = true;
                currentEmail = email;
                break;
            }
        }
        currentEmail.click();
        BrowserUtil.wait(5);
        assertTrue(actualResult);

        String replyEmailActual = driver.findElement(replyEmailBy).getText().trim();
        assertEquals(replyEmailActual,replyEmailExpected);

        String replySubjectActual = driver.findElement(replySubjectBy).getText().trim();
        String replySubjectExpected = "Thanks for subscribing to practice.cybertekschool.com!";
        assertEquals(replySubjectActual,replySubjectExpected);
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(emailURL);
        driver.manage().window().maximize();
        BrowserUtil.wait(3);
        email = driver.findElement(emailBy).getText();
        driver.navigate().to(URL);

    }


    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }

}
