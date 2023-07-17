package com.selenium;

import com.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class OpenBrowser {
    public WebDriver process(User user) {
        try {
            System.setProperty("webdriver.chrome.driver", user.getDriverPath());
            /// 1.创建webdriver驱动
//            new ChromeOptions().addArguments("--headless");
            WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
//            WebDriver driver = new ChromeDriver();
            // 2.打开首页
            driver.get(user.getUrl());
            //3.
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
