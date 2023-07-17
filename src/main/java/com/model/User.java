package com.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class User {
    private String url;
    private String driverPath;
    private String userId;
    private String pwd;
    private String token;

}
