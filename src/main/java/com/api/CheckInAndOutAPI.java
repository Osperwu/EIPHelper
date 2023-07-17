package com.api;

import com.enums.CheckEnum;
import com.model.MyConfig;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.model.Constants.*;

@Service
public class CheckInAndOutAPI extends PostAction {
    @Autowired
    MyConfig myConfig;

    public String sendPunch(String accessToken, String type) throws Exception {
        JSONObject req = new JSONObject();
        req.put("type", type);
        req.put("geoLocation", getLocation());
        //https://eipdev.1177tech.com.tw/EipConnect/app/home/punch
        Request request = buildReq(req, myConfig.getPunchUrl(), accessToken);
        JSONObject resp = send(request);
        System.out.println(resp);
        String typeDesc = type.equals(CheckEnum.checkIn.getType()) ? CheckEnum.checkIn.getTypeDesc() : CheckEnum.checkOut.getTypeDesc();
        if (resp.optString(RC).equals(success)) {
            return typeDesc + " 打卡成功";
        } else if (resp.optString(RC).equals(already)) {
            return typeDesc + " 已經打過卡";
        } else {
            throw new Exception(typeDesc + " 打卡錯誤 : " + resp.optString(RM));
        }
    }

    private static JSONObject getLocation() {
        int random = (int) (Math.random() * 100);
        String num = String.valueOf(random);
        StringBuffer sb = new StringBuffer(num);
        if (num.length() < 2) {
            sb.append("6");
        }
        JSONObject location = new JSONObject();
        location.put("latitude", "25.0751" + sb.toString());
        location.put("longitude", "121.5749" + sb.toString());
//        location.put("latitude", "25.075153");
//        location.put("longitude", "121.574966");
        return location;
    }
}
