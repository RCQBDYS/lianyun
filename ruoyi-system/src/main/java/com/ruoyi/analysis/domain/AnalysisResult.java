package com.ruoyi.analysis.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 analysis_result
 * 
 * @author ruoyi
 * @date 2020-10-31
 */
public class AnalysisResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** log分析结果id */
    private Long resultId;

    /** 结果名称 */
    @Excel(name = "结果名称")
    private String resultName;

    /** 结果的存储路径 */
    @Excel(name = "结果的存储路径")
    private String resultPath;

    /** 结果的生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结果的生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date resultCreattime;

    /** log文件的对应的id */
    @Excel(name = "script文件的对应的id")
    private Long scriptId;

    public void setResultId(Long resultId) 
    {
        this.resultId = resultId;
    }

    public Long getResultId() 
    {
        return resultId;
    }
    public void setResultName(String resultName) 
    {
        this.resultName = resultName;
    }

    public String getResultName() 
    {
        return resultName;
    }
    public void setResultPath(String resultPath) 
    {
        this.resultPath = resultPath;
    }

    public String getResultPath() 
    {
        return resultPath;
    }
    public void setResultCreattime(Date resultCreattime) 
    {
        this.resultCreattime = resultCreattime;
    }

    public Date getResultCreattime() 
    {
        return resultCreattime;
    }
    public void setScriptId(Long scriptId)
    {
        this.scriptId = scriptId;
    }

    public Long getScriptId()
    {
        return scriptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("resultName", getResultName())
            .append("resultPath", getResultPath())
            .append("resultCreattime", getResultCreattime())
            .append("scriptId", getScriptId())
            .toString();
    }
}
