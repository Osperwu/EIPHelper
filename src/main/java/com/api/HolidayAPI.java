package com.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Holiday;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayAPI {
    public String getHoliday(String year) throws Exception {
        String HOLIDAY_API_URL = "https://cdn.jsdelivr.net/gh/ruyut/TaiwanCalendar/data/" + year + ".json";

        OkHttpClient client = new OkHttpClient();
        // 獲取當月的所有國定假日
        Request request = new Request.Builder()
                .url(HOLIDAY_API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Failed to get holidays. Response code: {}" + response);
                String msg = String.format("Failed to get holidays. Response code: {%s}", response.toString());
                throw new Exception(msg);
            }
            String responseStr = response.body().string();
            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        String holiday = new HolidayAPI().getHoliday("2023");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Holiday> holidays = objectMapper.readValue(holiday, new TypeReference<List<Holiday>>() {});
        holidays.forEach(e-> System.err.println(e));
    }
}
