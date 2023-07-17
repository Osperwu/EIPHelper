package com.line;

import okhttp3.*;

import java.io.IOException;

public class LineNotifyService {
    public static String token;

    public LineNotifyService() {
    }

    public LineNotifyService(String token) {
        this.token = token;
    }

    private final OkHttpClient client = new OkHttpClient();

    public String sendNotification(String message) {
        try {
            RequestBody requestBody = new FormBody.Builder()
                    .add("message", message)
                    .build();

            Request request = new Request.Builder()
                    .url("https://notify-api.line.me/api/notify")
                    .addHeader("Authorization", "Bearer " + token)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
//            throw new IOException("Unexpected response code " + response);
                return "Unexpected response code " + response;
            } else {
                return "Line Notify message sent successfully. msg = " + message;
            }
//        System.out.println("Line Notify message sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
