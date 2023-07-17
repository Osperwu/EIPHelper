package com.action.apiAction;

import com.Utils.WebUtil;
import com.line.LineNotifyService;
import com.model.Constants;
import com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.model.Constants.getNow;

/**
 * @implNote 打下班卡-API
 */
@Service
public class OutAPIAction extends BaseAction {
    // 獲取日誌記錄器
    private static final Logger logger = LoggerFactory.getLogger(OutAPIAction.class);

    @Override
    public void process(User user) {
        String resp = null;
        try {
            String accessToken = loginAPI.sendLogin(user);
            Thread.sleep(1000);
            resp = checkInAndOutAPI.sendPunch(accessToken, Constants.out);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("打下班卡錯誤 : " + e);
            resp = e.toString();
        } finally {
            String msg = getNow() + " " + user.getUserId().replaceAll("@1177tech.com.tw", "") + " " + resp;
            String ans = new LineNotifyService(user.getToken()).sendNotification(msg);
            logger.error("打下班卡 : " + ans);
            System.out.println(ans);
        }

    }
}
