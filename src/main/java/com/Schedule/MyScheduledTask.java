package com.Schedule;

import com.Utils.CaptchaUtil;
import com.Utils.WebUtil;
import com.action.CheckInAction;
import com.action.CheckOutAction;
import com.action.apiAction.InAPIAction;
import com.action.apiAction.OutAPIAction;
import com.model.Holiday;
import com.model.MyConfig;
import com.model.User;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.model.Constants.getYearDate;


@Component
public class MyScheduledTask {
    @Autowired
    MyConfig myConfig;
    @Autowired
    CheckInAction checkInAction;
    @Autowired
    CheckOutAction checkOutAction;
    @Autowired
    InAPIAction inAPIAction;//API
    @Autowired
    OutAPIAction outAPIAction;//API
    @Scheduled(cron = "0 50 8 ? * MON-FRI", zone="Asia/Taipei")
    public void checkInTask() {
        System.out.println("啟動打上班卡排程");
        if (isHoliday()){
            return;
        }
        // 任務執行的內容
        CaptchaUtil.dataPath = myConfig.getDataPath();
        User user = User.builder()
                .url(myConfig.getUrl())
                .driverPath(myConfig.getDriver())
                .token(myConfig.getToken())
                .userId(myConfig.getId())
                .pwd(myConfig.getPwd()).build();
        WebUtil.wait(5);
//        checkInAction.process(user);
        inAPIAction.process(user);
    }

    @Scheduled(cron = "0 10 18 ? * MON-FRI", zone="Asia/Taipei")
    public void checkOutTask() {
        System.out.println("啟動下班排程");
        if (isHoliday()){
            return;
        }
        // 任務執行的內容
        CaptchaUtil.dataPath = myConfig.getDataPath();
        User user = User.builder()
                .url(myConfig.getUrl())
                .driverPath(myConfig.getDriver())
                .token(myConfig.getToken())
                .userId(myConfig.getId())
                .pwd(myConfig.getPwd()).build();
//        WebUtil.wait(5);
//        checkOutAction.process(user);
        outAPIAction.process(user);
    }

    private boolean isHoliday() {
        List<Holiday> holidays = myConfig.getHolidays();
        Holiday holiday = holidays.stream()
                .filter(h -> h.getDate().equals(getYearDate()))
                .findAny().get();
        return holiday.getIsHoliday() ? true : false;
    }
}
