package com.ruoyi.analysis.service;

import java.util.List;
import com.ruoyi.analysis.domain.AnalysisLog;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-10-30
 */
public interface IAnalysisLogService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param logId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnalysisLog selectAnalysisLogById(Long logId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param analysisLog 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AnalysisLog> selectAnalysisLogList(AnalysisLog analysisLog);

    /**
     * 新增【请填写功能名称】
     * 
     * @param analysisLog 【请填写功能名称】
     * @return 结果
     */
    public int insertAnalysisLog(AnalysisLog analysisLog);

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisLog 【请填写功能名称】
     * @return 结果
     */
    public int updateAnalysisLog(AnalysisLog analysisLog);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param logIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisLogByIds(Long[] logIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param logId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisLogById(Long logId);
}
