package com.selenium;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import static com.Utils.WebUtil.click;
@Service
public class Logout {
    public void process(WebDriver d) {
        try {
            //投像
            click(d, "//*[@id='app']/div/div[1]/div[1]/div[1]/div[2]/div/div[2]/div/a[3]/div");
            Thread.sleep(1000);
            //登出
            click(d, "//*[@id='app']/div/div[1]/div[1]/div[2]/div/div/div/ul/li[3]/a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
