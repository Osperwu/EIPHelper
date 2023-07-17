package com.api;

import com.model.Constants;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import static com.model.Constants.*;

public abstract class PostAction {

    public Request buildReq(JSONObject req, String url) {
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(contentType, req.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Content-Type", "application/json")
                .build();
        return request;
    }

    public Request buildReq(JSONObject req, String url, String accessToken) {
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(contentType, req.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", accessToken)
                .post(requestBody)
                .header("Content-Type", "application/json")
                .build();
        return request;
    }

    public JSONObject send(Request request) throws IOException {
        Response response = new OkHttpClient().newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected response code " + response);
        } else {
            String resp = response.body().string().toString();
            JSONObject jResp = new JSONObject(resp);
            response.close();
            return jResp;
        }
    }

    public String getToken(JSONObject resp) throws Exception {
        try {
            String t = new JSONObject(resp.optString(data)).optString(accessToken);
            if (Objects.isNull(t) || t.isEmpty()) {
                throw new Exception("登入失敗，toke = " + t);
            }
            return t;
        } catch (Exception e) {
            throw new Exception("登入失敗，無法取得" + accessToken);
        }
    }
}
