package com.example.sports.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-29
 */
public class RoleRes {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "学号")
    private Integer sId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "权限名称 1 项目管理 2 成绩查询 3 学生报名管理 4 成绩录入 5 权限管理")
    private List roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getRoleName() {
        return roleName;
    }

    public void setRoleName(List roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
