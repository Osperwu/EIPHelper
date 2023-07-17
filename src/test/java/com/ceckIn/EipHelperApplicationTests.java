package com.ceckIn;

import com.Utils.CaptchaUtil;
import com.action.CheckInAction;
import com.action.apiAction.InAPIAction;
import com.action.apiAction.OutAPIAction;
import com.model.MyConfig;
import com.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EipHelperApplicationTests {
    @Autowired
    MyConfig myConfig;
    @Autowired
    CheckInAction checkInAction;
    @Autowired
    InAPIAction InAPIAction;
    @Autowired
    OutAPIAction outAPIAction;

    @Test
    void API() {
//        CaptchaUtil.dataPath = myConfig.getDataPath();
//        System.out.println("dataPath" + CaptchaUtil.dataPath);
//        User user = User.builder()
//                .url(myConfig.getUrl())
//                .driverPath(myConfig.getDriver())
//                .token(myConfig.getToken())
//                .userId(myConfig.getId())
//                .pwd(myConfig.getPwd()).build();
//        InAPIAction.process(user);
//        outAPIAction.process(user);
    }

    @Test
    void selenium() {
//		CaptchaUtil.dataPath = myConfig.getDataPath();
//		System.out.println("dataPath"+CaptchaUtil.dataPath);
//		User user = User.builder()
//				.url(myConfig.getUrl())
//				.driverPath(myConfig.getDriver())
//				.token(myConfig.getToken())
//				.userId(myConfig.getId())
//				.pwd(myConfig.getPwd()).build();
//		checkInAction.process(user);
    }


}
