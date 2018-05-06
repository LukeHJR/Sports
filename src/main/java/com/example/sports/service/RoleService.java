package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.RoleModiRequest;
import com.example.sports.dto.response.RoleRes;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-29
 */
public interface RoleService {

    /**
     * 添加新的管理员
     *
     * @param
     * @return
     */
    void addAdmin(RoleModiRequest request);

    /**
     * 修改管理员权限
     *
     * @param
     * @return
     */
    void modifyAdmin(Long id, List<Integer> roleId);

    /**
     * 删除管理员
     *
     * @param
     * @return
     */
    void delectAdmin(Long id);

    /**
     * 用户权限列表
     *
     * @param requestBean
     * @return
     */
    PageInfo<RoleRes> adminRoleList(PageRequestBean requestBean);

}
