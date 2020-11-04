package com.ruoyi.analysis.mapper;

import java.util.List;
import com.ruoyi.analysis.domain.AnalysisResult;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-31
 */
public interface AnalysisResultMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param scriptId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnalysisResult selectAnalysisResultByScriptId(Long scriptId);

    /**
     * @param resultId 结果id
     * @return 结果集
     */
    public AnalysisResult selectAnalysisResultById(Long resultId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param analysisResult 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AnalysisResult> selectAnalysisResultList(AnalysisResult analysisResult);

    /**
     * 新增【请填写功能名称】
     * 
     * @param analysisResult 【请填写功能名称】
     * @return 结果
     */
    public int insertAnalysisResult(AnalysisResult analysisResult);

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisResult 【请填写功能名称】
     * @return 结果
     */
    public int updateAnalysisResult(AnalysisResult analysisResult);

    /**
     * 删除【请填写功能名称】
     * 
     * @param resultId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisResultById(Long resultId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param resultIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnalysisResultByIds(Long[] resultIds);
}
