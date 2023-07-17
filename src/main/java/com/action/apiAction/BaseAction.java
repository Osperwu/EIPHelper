package com.action.apiAction;

import com.api.CheckInAndOutAPI;
import com.api.LoginAPI;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseAction {

    @Autowired
    LoginAPI loginAPI;
    @Autowired
    CheckInAndOutAPI checkInAndOutAPI;

    protected void process(User user) {
    }
}
