package com.example.sports.service;

import com.example.sports.dto.request.EnterInfoRequest;
import com.example.sports.dto.request.EnterRequest;
import com.example.sports.dto.response.EnterInfoRes;
import com.example.sports.dto.response.RoleRes;
import com.example.sports.mapper.SysCollegeMapper;
import com.example.sports.mapper.SysProjectMapper;
import com.example.sports.mapper.SysProjectSignMapper;
import com.example.sports.mapper.SysUserMapper;
import com.example.sports.model.SysCollege;
import com.example.sports.model.SysCollegeExample;
import com.example.sports.model.SysProjectSign;
import com.example.sports.model.SysProjectSignExample;
import com.example.sports.model.SysUser;
import com.example.sports.model.SysUserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
@Service
public class enterServiceImpl implements enterService{

    @Autowired
    private SysProjectSignMapper sysProjectSignMapper;

    @Autowired
    private SysCollegeMapper sysCollegeMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysProjectMapper sysProjectMapper;

    @Override
    public void enter(EnterRequest request) {
        SysProjectSign sysProjectSign = new SysProjectSign();
        sysProjectSign.setSportId(String.valueOf(request.getSysProjectId()));
        sysProjectSign.setSportType(request.getSportType());
        sysProjectSign.setSysUserSid(request.getSysUserSid());
        sysProjectSign.setTeamType(request.getTeamType());
        sysProjectSignMapper.insertSelective(sysProjectSign);
    }

    @Override
    public PageInfo<EnterInfoRes> enterInfo(EnterInfoRequest request) {

        SysProjectSignExample example = new SysProjectSignExample();
        SysProjectSignExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(request.getSysUserSid())){
            criteria.andSysUserSidEqualTo(request.getSysUserSid());
        }
        if (request.getCollegeId() != null && request.getCollegeId() > 0){
            criteria.andSysCollegeIdEqualTo(request.getCollegeId());
        }
        List<EnterInfoRes> res = new ArrayList<>();//新建实体类List
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), true);//启动分页，接下来第一条为分页对象
        List<SysProjectSign> sysProjectSignList = sysProjectSignMapper.selectByExample(example);
        PageInfo<EnterInfoRes> resPage = new PageInfo(sysProjectSignList);//放入pageInfo工具类中
        for (SysProjectSign sysProjectSign : sysProjectSignList){
            EnterInfoRes dto = new EnterInfoRes();
            SysCollege sysCollege =sysCollegeMapper.selectByPrimaryKey(Long.valueOf(request.getCollegeId()));
            dto.setCollgeName(Integer.valueOf(sysCollege.getName()));

            dto.setSysUserSid(sysProjectSign.getSysUserSid());
            SysUserExample example1 = new SysUserExample();
            SysUserExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andSidEqualTo(sysProjectSign.getSysUserSid());
            List<SysUser> sysUser = sysUserMapper.selectByExample(example1);
            if (sysUser.size() > 0 && sysUser != null){
                dto.setStudentName(sysUser.get(0).getName());
            }
            dto.setSysProject(sysProjectMapper.selectByPrimaryKey(sysProjectSign.getSysProjectId().longValue()).getName());
            dto.setTeamType(sysProjectSign.getTeamType());
            dto.setSportType(sysProjectSign.getSportType());
            dto.setSportId(sysProjectSign.getSportId());
            res.add(dto);
        }
        PageInfo<EnterInfoRes> pageInfo = new PageInfo(res);//将循环遍历后的新建List中元素放入返回PageInfo中
        pageInfo.setPages(resPage.getPages());//总页数
        pageInfo.setTotal(resPage.getTotal());//总条数
        pageInfo.setPageSize(resPage.getPageSize());//每页大小
        pageInfo.setPageNum(resPage.getPageNum());//当前页
        return pageInfo;//返回分页完成的数据
    }

    @Override
    public List<SysCollege> sysCollegeInfo() {
        SysCollegeExample example = new SysCollegeExample();
        SysCollegeExample.Criteria criteria = example.createCriteria();
        List<SysCollege> sysColleges = sysCollegeMapper.selectByExample(example);
        return sysColleges;
    }
}
