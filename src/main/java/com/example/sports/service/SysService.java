package com.example.sports.service;

import com.example.sports.dto.request.LoginRequest;
import com.example.sports.dto.request.RegistRequest;
import com.example.sports.dto.response.LoginRes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-24
 */
public interface SysService {

    /**
     * 登录
     *
     * @param request
     * @return
     */
    LoginRes login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse req);

    /**
     * 注册
     *
     * @param request
     * @return
     */
    void regist(RegistRequest registRequest, HttpServletRequest request, HttpServletResponse req);
}
