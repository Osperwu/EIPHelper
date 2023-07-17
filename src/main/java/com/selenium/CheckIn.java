package com.selenium;

import com.Utils.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.Utils.WebUtil.*;

@Service
public class CheckIn {
    public String process(WebDriver d) throws Exception {
        try {
            String resp;
            //打卡
            Thread.sleep(500);
            click(d, "//*[@id='app']/div/div[1]/div[2]/div[1]/div[1]/div/div[1]");
            Thread.sleep(500);
            //上班羅
            String in = "//*[@id='app']/div/div[1]/div[2]/div[3]/div/div[2]/div/div[2]/div[1]/button";
            WebElement we = d.findElement(By.xpath(in));
            if (we.isEnabled()) {
                System.out.println("網頁元素沒有被禁用");
                Thread.sleep(1000);
                click(d, in);
                String eleText = getEleText(d, "//*[@id='app']/div/div[1]/div[2]/div[3]/div/div[2]/div/p");
                System.out.println("打卡錯誤訊息" + eleText);
                if (Objects.nonNull(eleText) && eleText.equals("打卡失敗")) {
//                    throw new Exception(eleText);
                    throw new Exception("打卡失敗，請登入確認");
                }
                resp = "checkIn success";
                resp = "打卡成功";
            } else {
                System.out.println("already checkIn");
                resp = "已經打過卡";
                return resp;
            }
            WebUtil.wait(5);
            //關閉視窗
            click(d, "//*[@id='app']/div/div[1]/div[2]/div[3]/div/div[1]/div/a");
//            Thread.sleep(1000);

            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
