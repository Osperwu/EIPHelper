package com.action;

import com.line.LineNotifyService;
import com.model.User;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import static com.model.Constants.getNow;

/**
 * @implNote 打下班卡-爬蟲
 */
@Service
public class CheckOutAction extends BaseAction {
    @Override
    public void process(User user) {
        WebDriver d = openBrowser.process(user);
        String resp;
        try {
            login.process(d, user);
            resp = checkOut.process(d);
            logout.process(d);
        } catch (Exception e) {
            e.printStackTrace();
            resp = e.toString();
        } finally {
            d.quit();
        }
        String ans = new LineNotifyService(user.getToken()).sendNotification(getNow() + " " + resp);
        System.out.println(ans);
    }
}
