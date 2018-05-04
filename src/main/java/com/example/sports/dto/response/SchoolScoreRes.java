package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-03
 */
public class SchoolScoreRes {

    @ApiModelProperty(value = "学号")
    private String sysUserSid;

    @ApiModelProperty(value = "项目id")
    private Integer sysProjectId;

    @ApiModelProperty(value = "组别 1、女子组 2、男子组")
    private Integer teamType;

    @ApiModelProperty(value = "项目类别 1、田赛 2、径赛")
    private Integer sportType;

    @ApiModelProperty(value = "个人成绩")
    private Integer achievement;

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid;
    }

    public Integer getSysProjectId() {
        return sysProjectId;
    }

    public void setSysProjectId(Integer sysProjectId) {
        this.sysProjectId = sysProjectId;
    }

    public Integer getTeamType() {
        return teamType;
    }

    public void setTeamType(Integer teamType) {
        this.teamType = teamType;
    }

    public Integer getSportType() {
        return sportType;
    }

    public void setSportType(Integer sportType) {
        this.sportType = sportType;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
