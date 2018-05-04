package com.example.sports.service;

import com.example.sports.constant.ErrorContants;
import com.example.sports.constant.Errors;
import com.example.sports.dto.request.LoginRequest;
import com.example.sports.dto.request.RegistRequest;
import com.example.sports.dto.response.LoginRes;
import com.example.sports.exception.BusinessException;
import com.example.sports.mapper.SysUserMapper;
import com.example.sports.mapper.SysUserStudentMapper;
import com.example.sports.model.SysUser;
import com.example.sports.model.SysUserExample;
import com.example.sports.model.SysUserStudent;
import com.example.sports.service.Session.SessionService;
import com.example.sports.util.Md5Util;
import com.example.sports.util.PasswordUtil;
import com.example.sports.util.SysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-24
 */
@Service
public class SysServiceImpl implements SysService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserStudentMapper sysUserStudentMapper;

    @Override
    public LoginRes login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse req) {
        SysUser sysUser = SysUtil.getSysUserBySid(loginRequest.getSid());
        if (sysUser == null) {
            throw new BusinessException(ErrorContants.NO_AVALIABLE_DATA, "查不到此用户信息");
        }
        if (!PasswordUtil.encode(loginRequest.getPassword()).equalsIgnoreCase(sysUser.getPassword())) {
            throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "账号或密码错误");
        }
        if (sysUser.getFrozen()) {
            throw new BusinessException(ErrorContants.PERMISSION_DENY, "您的账号被冻结，请联系管理员");
        }
        sessionService.setManager(request, sysUser);
        LoginRes loginRes = new LoginRes();
        loginRes.setId(sysUser.getId().intValue());
        loginRes.setType(sysUser.getType());
        loginRes.setSid(sysUser.getSid());
        loginRes.setName(sysUser.getName());
        loginRes.setAvatar(sysUser.getAvatar());
        return loginRes;
    }

    @Override
    public void regist(RegistRequest registRequest, HttpServletRequest request, HttpServletResponse req) {
        SysUser sysUser = SysUtil.getSysUserBySid(registRequest.getSid());
        if (sysUser != null) {
            throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "此用户已注册！");
        }
        if (sysUser == null){
            try {
                SysUser sysUser1 = new SysUser();
                sysUser1.setSid(registRequest.getSid());
                sysUser1.setName(registRequest.getName());
                registRequest.setType((short) 3);
                if (registRequest.getType() != 3){
                    throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE, "❌！");
                }
                sysUser1.setType(registRequest.getType());
                if (registRequest.getAvatar() != null){
                    sysUser1.setAvatar(registRequest.getAvatar());
                }
                if (!PasswordUtil.match(registRequest.getPassword(),PasswordUtil.encode(registRequest.getRePassword()))){
                    throw new BusinessException(ErrorContants.PARAMS_INAVAILABLE,"❌！");
                }
                sysUser1.setPassword(PasswordUtil.encode(registRequest.getPassword()));
                sysUserMapper.insertSelective(sysUser1);//创建用户账号

                SysUserStudent sysUserStudent = new SysUserStudent();
                sysUserStudent.setSysUserSid(registRequest.getSid());
                sysUserStudent.setSysCollege(registRequest.getCollegeId());
                sysUserStudentMapper.insertSelective(sysUserStudent);//创建学生账号
//                req.sendRedirect("/static/loginH.html");
            }catch (Exception e){
                e.printStackTrace();
                throw new BusinessException(Errors.SYSTEM_REQUEST_PARAM_ERROR,"请输入正确的注册信息");
            }
        }
    }
}
