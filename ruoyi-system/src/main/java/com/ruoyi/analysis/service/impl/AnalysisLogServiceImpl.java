package com.ruoyi.analysis.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.analysis.mapper.AnalysisLogMapper;
import com.ruoyi.analysis.domain.AnalysisLog;
import com.ruoyi.analysis.service.IAnalysisLogService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-30
 */
@Service
public class AnalysisLogServiceImpl implements IAnalysisLogService 
{
    @Autowired
    private AnalysisLogMapper analysisLogMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param logId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnalysisLog selectAnalysisLogById(Long logId)
    {
        return analysisLogMapper.selectAnalysisLogById(logId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param analysisLog 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnalysisLog> selectAnalysisLogList(AnalysisLog analysisLog)
    {
        return analysisLogMapper.selectAnalysisLogList(analysisLog);
    }

    /**
     * 新增【log文件】
     * 
     * @param analysisLog 【log文件的添加】
     * @return 结果
     */
    @Override
    public int insertAnalysisLog(AnalysisLog analysisLog)
    {
        analysisLog.setLogTime(DateUtils.getNowDate());
        return analysisLogMapper.insertAnalysisLog(analysisLog);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnalysisLog(AnalysisLog analysisLog)
    {
        analysisLog.setLogTime(DateUtils.getNowDate());
        return analysisLogMapper.updateAnalysisLog(analysisLog);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param logIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisLogByIds(Long[] logIds)
    {
        return analysisLogMapper.deleteAnalysisLogByIds(logIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param logId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisLogById(Long logId)
    {
        return analysisLogMapper.deleteAnalysisLogById(logId);
    }
}
