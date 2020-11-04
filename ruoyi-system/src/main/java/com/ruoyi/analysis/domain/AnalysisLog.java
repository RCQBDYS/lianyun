package com.ruoyi.analysis.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 analysis_log
 * 
 * @author ruoyi
 * @date 2020-10-30
 */
public class AnalysisLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** log文件id */
    private Long logId;

    /** log名称 */
    @Excel(name = "log名称")
    private String logName;

    /** log路径 */
    @Excel(name = "log路径")
    private String logPath;

    /** log文件的上传者 */
    @Excel(name = "log文件的上传者")
    private String logUploader;

    /** log文件的上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "log文件的上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logTime;

    /** 脚本id */
    @Excel(name = "脚本id")
    private Long scriptId;

    public void setLogId(Long logId) 
    {
        this.logId = logId;
    }

    public Long getLogId() 
    {
        return logId;
    }
    public void setLogName(String logName) 
    {
        this.logName = logName;
    }

    public String getLogName() 
    {
        return logName;
    }
    public void setLogPath(String logPath) 
    {
        this.logPath = logPath;
    }

    public String getLogPath() 
    {
        return logPath;
    }
    public void setLogUploader(String logUploader) 
    {
        this.logUploader = logUploader;
    }

    public String getLogUploader() 
    {
        return logUploader;
    }
    public void setLogTime(Date logTime) 
    {
        this.logTime = logTime;
    }

    public Date getLogTime() 
    {
        return logTime;
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
            .append("logId", getLogId())
            .append("logName", getLogName())
            .append("logPath", getLogPath())
            .append("logUploader", getLogUploader())
            .append("logTime", getLogTime())
            .append("scriptId", getScriptId())
            .toString();
    }
}
