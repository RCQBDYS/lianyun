package com.ruoyi.analysis.mapper;

import java.util.List;
import com.ruoyi.analysis.domain.AnalysisLog;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-30
 */
public interface AnalysisLogMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param logId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisLogById(Long logId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnalysisLogByIds(Long[] logIds);
}
