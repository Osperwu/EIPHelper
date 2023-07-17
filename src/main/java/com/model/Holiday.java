package com.model;

import lombok.Data;

@Data
public class Holiday {
    private String date;
    private String week;
    private String isHoliday;
    private String description;

    public boolean getIsHoliday() {
        if (isHoliday.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}
