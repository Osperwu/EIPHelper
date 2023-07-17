package com.selenium;

import com.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.Utils.CaptchaUtil.getContent;
import static com.Utils.WebUtil.click;
import static com.Utils.WebUtil.isElement;

@Service
public class Login {
    public void process(WebDriver d, User user) throws Exception {
        try {
            String ans;
            WebElement id = d.findElement(By.xpath("//*[@id='app']/div/form/div/div/div[1]/input"));
            id.sendKeys(user.getUserId());
            WebElement pwd = d.findElement(By.xpath("//*[@id='app']/div/form/div/div/div[2]/input"));
            pwd.sendKeys(user.getPwd());

            WebElement we = d.findElement(By.xpath("//*[@id='app']/div/form/div/div/div[3]/img"));
            File screenshot = we.getScreenshotAs(OutputType.FILE);

            while (true) {
                ans = getContent(screenshot).replaceAll("[\\D]", "").trim();
                if (ans.isEmpty() || ans.length() == 4) {
                    break;
                } else {
                    //重整驗證碼
                    click(d, "//*[@id='app']/div/form/div/div/div[3]/div[1]");
                }
            }
            WebElement captcha = d.findElement(By.xpath("//*[@id='app']/div/form/div/div/div[3]/div[2]/input"));
            captcha.sendKeys(ans);

            Thread.sleep(2000);
            click(d, "//*[@id='app']/div/form/div/div/button[1]");
            Thread.sleep(2000);
            if (d.getCurrentUrl().contains("login") && isElement(d, "//*[@id='app']/div/form/div/div/div[4]/span")) {
                String msg = d.findElement(By.xpath("//*[@id='app']/div/form/div/div/div[4]/span")).getText();
                if (msg.equals("帳號已鎖定，請稍後再試")) {
                    throw new Exception("帳號已鎖定，請稍後再試");
                } else if (msg.equals("圖形驗證錯誤")) {
                    //重整驗證碼
                    d.navigate().refresh();
                    System.out.println("登入失敗，驗證碼錯誤");
                    process(d, user);
                }
            } else if (d.getCurrentUrl().contains("changePassword")) {
                click(d, "//*[@id='app']/div/div[2]/div/div/div/button[2]");
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
