package com.ruoyi.analysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 analysis_project
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public class AnalysisProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 测试人员id */
    @Excel(name = "测试人员id")
    private String testId;

    /** 开发人员id */
    @Excel(name = "开发人员id")
    private String developId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setTestId(String testId) 
    {
        this.testId = testId;
    }

    public String getTestId() 
    {
        return testId;
    }
    public void setDevelopId(String developId) 
    {
        this.developId = developId;
    }

    public String getDevelopId() 
    {
        return developId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("testId", getTestId())
            .append("developId", getDevelopId())
            .append("remark", getRemark())
            .toString();
    }
}
