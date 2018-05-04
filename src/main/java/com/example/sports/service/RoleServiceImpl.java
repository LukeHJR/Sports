package com.example.sports.service;


import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.request.RoleModiRequest;
import com.example.sports.dto.response.RoleRes;
import com.example.sports.mapper.SysModuleMapper;
import com.example.sports.mapper.SysUserMapper;
import com.example.sports.mapper.SysUserPtMapper;
import com.example.sports.model.SysModule;
import com.example.sports.model.SysModuleExample;
import com.example.sports.model.SysUser;
import com.example.sports.model.SysUserExample;
import com.example.sports.model.SysUserPt;
import com.example.sports.model.SysUserPtExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-29
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired//可以用@Resource
    private SysUserPtMapper sysUserPtMapper;//自动注入Mapper

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Override
    public void addAdmin(RoleModiRequest request) {
        if (request.getsId() != null && StringUtils.isNotBlank(request.getName()) && request.getRoleId().size() != 0) {
            SysUserPt sysUserPt = new SysUserPt();

            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andSidEqualTo(String.valueOf(request.getsId()));
            SysUser sysUser = sysUserMapper.selectByExample(example).get(0);

            sysUserPt.setSysUserId(sysUser.getId().intValue());

            List<Integer> roleId = request.getRoleId();
            String roleName = null;
            for (int i = 0; i < roleId.size(); i++) {
                SysModule sysModule = sysModuleMapper.selectByPrimaryKey(Long.valueOf(roleId.get(i)));
                roleName += sysModule.getModuleName() + "";
                sysUserPt.setRoleName(roleName);
            }
            sysUserPtMapper.insertSelective(sysUserPt);
        }
    }

    @Override
    public void modifyAdmin(Long id, List<Integer> roleId) {
        SysUserPt sysUserPt = new SysUserPt();
        sysUserPt.setId(id);
        String roleName = null;
        for (int i = 0; i < roleId.size(); i++) {
            SysModule sysModule = sysModuleMapper.selectByPrimaryKey(Long.valueOf(roleId.get(i)));
            roleName += sysModule.getModuleName() + "";
            sysUserPt.setRoleName(roleName);
        }
        sysUserPtMapper.insertSelective(sysUserPt);
    }

    @Override
    public void delectAdmin(Long id) {
        sysUserPtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<RoleRes> adminRoleList(PageRequestBean requestBean) {//传参为工具类/已内置

        SysUserPtExample example = new SysUserPtExample();
        SysUserPtExample.Criteria criteria = example.createCriteria();

        List<RoleRes> res = new ArrayList<>();//新建实体类List

        //PageInfo工具类依赖
        //  <!--pagehelper-->
        //        <dependency>
        //            <groupId>com.github.pagehelper</groupId>
        //            <artifactId>pagehelper-spring-boot-starter</artifactId>
        //            <version>1.1.2</version>
        //        </dependency>
        //
        //        <dependency>
        //            <groupId>org.springframework.boot</groupId>
        //            <artifactId>spring-boot-configuration-processor</artifactId>
        //            <optional>true</optional>
        //        </dependency>
        PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);//启动分页，接下来第一条为分页对象
        List<SysUserPt> sysUserPtList = sysUserPtMapper.selectByExample(example);//mybatis查询语句

        PageInfo<RoleRes> resPage = new PageInfo(sysUserPtList);//放入pageInfo工具类中

        for (SysUserPt sysUserPt : sysUserPtList) {//循环List
            RoleRes dto = new RoleRes();
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(Long.valueOf(sysUserPt.getSysUserId()));
            dto.setsId(Integer.valueOf(sysUser.getSid()));
            BeanUtils.copyProperties(sysUserPt, dto);//copy对象
            res.add(dto);//放入新建实体类List中
        }
        PageInfo<RoleRes> pageInfo = new PageInfo(res);//将循环遍历后的新建List中元素放入返回PageInfo中
        pageInfo.setPages(resPage.getPages());//总页数
        pageInfo.setTotal(resPage.getTotal());//总条数
        pageInfo.setPageSize(resPage.getPageSize());//每页大小
        pageInfo.setPageNum(resPage.getPageNum());//当前页
        return pageInfo;//返回分页完成的数据
    }
}
