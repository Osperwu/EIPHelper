package com.action;

import com.line.LineNotifyService;
import com.model.User;
import com.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseAction {
    @Autowired
    OpenBrowser openBrowser;
    @Autowired
    Login login;
    @Autowired
    CheckIn checkIn;
    @Autowired
    CheckOut checkOut;
    @Autowired
    Logout logout;
    LineNotifyService line;

    protected void process(User user) {
    }
}
