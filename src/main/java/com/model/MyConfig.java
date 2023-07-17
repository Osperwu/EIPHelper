package com.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
public class MyConfig {
    private String id;
    private String pwd;
    private String url;
    private String loginUrl;
    private String punchUrl;
    private String driver;
    private String token;
    private String dataPath;
    private List<Holiday> holidays;

    @Override
    public String toString() {
        return "MyConfig{" +
                "id='" + id + '\'' +
                ", pwd='" + tranPwd(pwd) + '\'' +
                ", url='" + url + '\'' +
                ", loginUrl='" + loginUrl + '\'' +
                ", punchUrl='" + punchUrl + '\'' +
                ", driver='" + driver + '\'' +
                ", token='" + token + '\'' +
                ", dataPath='" + dataPath + '\'' +
                '}';
    }

    private String tranPwd(String pwd) {
        return pwd.replaceAll("[\\D]", "*")
                .replaceAll("[\\d]", "*")
                .trim();
    }
}
