package com.example.sports.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author hjr
 * @date 2018-05-03
 *
 */
public class SysUserStudent implements Serializable {
    /**  */
    private Long id;

    /** 学号 */
    private String sysUserSid;

    /** 学院id */
    private Integer sysCollege;

    /** 积分 */
    private Integer integral;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysUserSid() {
        return sysUserSid;
    }

    public void setSysUserSid(String sysUserSid) {
        this.sysUserSid = sysUserSid == null ? null : sysUserSid.trim();
    }

    public Integer getSysCollege() {
        return sysCollege;
    }

    public void setSysCollege(Integer sysCollege) {
        this.sysCollege = sysCollege;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sysUserSid=").append(sysUserSid);
        sb.append(", sysCollege=").append(sysCollege);
        sb.append(", integral=").append(integral);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}