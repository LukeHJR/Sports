package com.example.sports.service;

import com.example.sports.dto.PageRequestBean;
import com.example.sports.dto.response.ScoreSerchRes;
import com.example.sports.mapper.SysCollegeMapper;
import com.example.sports.mapper.SysGradingModuleMapper;
import com.example.sports.mapper.SysProjectMapper;
import com.example.sports.mapper.SysUserStudentMapper;
import com.example.sports.model.SysGradingModule;
import com.example.sports.model.SysGradingModuleExample;
import com.example.sports.model.SysUserStudent;
import com.example.sports.model.SysUserStudentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class ScoreSerchServiceImpl implements ScoreSerchService {

    @Autowired
    private SysUserStudentMapper sysUserStudentMapper;

    @Autowired
    private SysGradingModuleMapper sysGradingModuleMapper;

    @Autowired
    private SysCollegeMapper sysCollegeMapper;

    @Autowired
    private SysProjectMapper sysProjectMapper;


    @Override
    public PageInfo<ScoreSerchRes> studentInfo(PageRequestBean requestBean, Long sid) {

        if (sid != null && sid > 0) {
            SysUserStudentExample studentExample = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteriaS = studentExample.createCriteria();
            criteriaS.andSysUserSidEqualTo(String.valueOf(sid));
            List<SysUserStudent> sysUserStudents = sysUserStudentMapper.selectByExample(studentExample);
            String id = null;
            if (sysUserStudents.size() > 0) {
                id = sysUserStudents.get(0).getSysUserSid();//学生账号表id
            }

            SysGradingModuleExample example = new SysGradingModuleExample();
            SysGradingModuleExample.Criteria criteria = example.createCriteria();
            criteria.andSysUserStudentIdEqualTo(Integer.valueOf(id));

            List<ScoreSerchRes> res = new ArrayList<>();
            PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);
            List<SysGradingModule> sysGradingModules = sysGradingModuleMapper.selectByExample(example);
            PageInfo<ScoreSerchRes> pageInfo = new PageInfo(sysGradingModules);
            for (SysGradingModule sysGradingModule : sysGradingModules) {
                ScoreSerchRes dto = new ScoreSerchRes();
                dto.setSportType(sysGradingModule.getSportType());
                dto.setAchievement(sysGradingModule.getAchievement());
                dto.setTeamType(sysGradingModule.getTeamType());
                dto.setMatchType(sysGradingModule.getMatchType());
                dto.setSysProjectName(sysProjectMapper.selectByPrimaryKey(sysGradingModule.getSysProjectId().longValue()).getName());
                dto.setAchievement(sysGradingModule.getAchievement());
                SysUserStudent sysUserStudent = sysUserStudentMapper.selectByPrimaryKey(Long.valueOf(id));
                dto.setIntegral(sysUserStudent.getIntegral());
                res.add(dto);
            }
            PageInfo<ScoreSerchRes> resPageInfo = new PageInfo<>(res);
            resPageInfo.setPageNum(pageInfo.getPageNum());
            resPageInfo.setPageSize(pageInfo.getPageSize());
            resPageInfo.setTotal(pageInfo.getTotal());
            resPageInfo.setPages(pageInfo.getPages());
            return resPageInfo;
        }
        return null;
    }

    @Override
    public PageInfo<ScoreSerchRes> collegeInfo(PageRequestBean requestBean, Long collegeId) {
        if (collegeId != null && collegeId > 0) {
            SysUserStudentExample studentExample = new SysUserStudentExample();
            SysUserStudentExample.Criteria criteriaS = studentExample.createCriteria();
            criteriaS.andSysCollegeEqualTo(collegeId.intValue());//学院号
            List<SysUserStudent> sysUserStudents = sysUserStudentMapper.selectByExample(studentExample);
            String sid = null;
            String suserid = null;
            String collegeName = null;
            for (int i = 0; sysUserStudents.size() > 0; i++) {
                sid = sysUserStudents.get(i).getSysUserSid();//学号
                suserid = String.valueOf(sysUserStudents.get(i).getId());//学生账号表id
                collegeName = sysCollegeMapper.selectByPrimaryKey(collegeId).getName();//院名

                SysGradingModuleExample sysGradingModuleExample = new SysGradingModuleExample();
                SysGradingModuleExample.Criteria criteria = sysGradingModuleExample.createCriteria();
                criteria.andSysUserStudentIdEqualTo(Integer.valueOf(suserid));//学生账号表id
                List<ScoreSerchRes> res = new ArrayList<>();
                PageHelper.startPage(requestBean.getPageNum(), requestBean.getPageSize(), true);
                List<SysGradingModule> sysGradingModules = sysGradingModuleMapper.selectByExample(sysGradingModuleExample);
                PageInfo<ScoreSerchRes> pageInfo = new PageInfo(sysGradingModules);
                for (SysGradingModule sysGradingModule : sysGradingModules) {
                    ScoreSerchRes dto = new ScoreSerchRes();
                    dto.setAchievement(sysGradingModule.getAchievement());
                    dto.setSysUserSid(sid);
                    dto.setCollegename(collegeName);
                    dto.setMatchType(sysGradingModule.getMatchType());
                    dto.setTeamType(sysGradingModule.getTeamType());
                    dto.setSportType(sysGradingModule.getSportType());
                    dto.setSysProjectName(sysProjectMapper.selectByPrimaryKey(sysGradingModule.getSysProjectId().longValue()).getName());
                    res.add(dto);
                }
                PageInfo<ScoreSerchRes> resPageInfo = new PageInfo<>(res);
                resPageInfo.setPageNum(pageInfo.getPageNum());
                resPageInfo.setPageSize(pageInfo.getPageSize());
                resPageInfo.setTotal(pageInfo.getTotal());
                resPageInfo.setPages(pageInfo.getPages());
                return resPageInfo;
            }

        }
        return null;
    }
}
