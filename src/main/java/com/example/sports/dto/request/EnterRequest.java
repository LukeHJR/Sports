package com.example.sports.dto.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-05-02
 */
public class EnterRequest {

    @ApiModelProperty(value = "学号")
    private String sysUserSid;

    @ApiModelProperty(value = "项目id")
    private Integer sysProjectId;

    @ApiModelProperty(value = "组别 1、女子组 2、男子组")
    private Integer teamType;

    @ApiModelProperty(value = "项目类别 1、田赛 2、径赛")
    private Integer sportType;

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
}
