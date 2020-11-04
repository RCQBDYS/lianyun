package com.ruoyi.analysis.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 analysis_script
 *
 * @author ruoyi
 * @date 2020-10-26
 */
public class AnalysisScript extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 脚本id
     */
    private Long id;

    /**
     * 脚本名称
     */
    @Excel(name = "脚本名称")
    private String scriptName;

    /**
     * 项目存放路径
     */
    @Excel(name = "项目存放路径")
    private String scriptPath;

    /**
     * 脚本上传者
     */
    @Excel(name = "脚本上传者")
    private String creater;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    private Long projectId;

    /**
     * log文件
     */
    private AnalysisLog analysisLog;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreater() {
        return creater;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public AnalysisLog getAnalysisLog() {
        return analysisLog;
    }

    public void setAnalysisLog(AnalysisLog analysisLog) {
        this.analysisLog = analysisLog;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("scriptName", getScriptName())
                .append("scriptPath", getScriptPath())
                .append("creater", getCreater())
                .append("createTime", getCreateTime())
                .append("projectId", getProjectId())
                .append("log", getAnalysisLog())
                .toString();
    }
}
