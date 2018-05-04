package com.example.sports.dto.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-24
 */
public class LoginRequest {

    @ApiModelProperty(value = "学号/教师编号")
    private String sid;

    @ApiModelProperty(value = "密码")
    private String password;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
