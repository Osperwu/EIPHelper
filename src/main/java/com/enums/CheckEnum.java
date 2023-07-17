package com.enums;

public enum CheckEnum {

    checkIn("in", "上班"),
    checkOut("out", "下班");

    private String type;
    private String typeDesc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    CheckEnum(String type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }
}
