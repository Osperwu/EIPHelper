package com.api;

import com.model.MyConfig;
import com.model.User;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.model.Constants.*;

@Service
public class LoginAPI extends PostAction {
    @Autowired
    MyConfig myConfig;

    public String sendLogin(User user) throws Exception {
        try {
            JSONObject req = new JSONObject();
            req.put("email", user.getUserId());
            req.put("password", user.getPwd());
            //https://eipdev.1177tech.com.tw/EipConnect/app/user/login
            Request request = buildReq(req, myConfig.getLoginUrl());
            JSONObject resp = send(request);
            if (resp.optString(RC).equals(success)) {
                String t = getToken(resp);
                return t;
            } else {
                throw new Exception("登入錯誤 : " + resp.optString(RM));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
