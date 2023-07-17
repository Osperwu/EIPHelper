package com.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class WebUtil {

    public static void click(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.click();
        } catch (Exception e) {
        }
    }
    public static void clickAndCheck(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.click();
        } catch (Exception e) {
            getEleText(driver, xpath);
        }
    }

    public static boolean isElement(WebDriver driver, String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String getEleText(WebDriver driver, String xpath) {
        try {
            return  driver.findElement(By.xpath(xpath)).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static void wait(int num) {
        try {
            int random = (int) (Math.random() * num);
            System.out.println("等待" + random + "分鐘");
            TimeUnit.MINUTES.sleep(random);
        } catch (Exception e) {
        }
    }
}
