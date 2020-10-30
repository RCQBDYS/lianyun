package com.ruoyi.analysis.mapper;

import java.util.List;
import com.ruoyi.analysis.domain.AnalysisScript;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-26
 */
public interface AnalysisScriptMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnalysisScript selectAnalysisScriptById(Long id);

    /**
     *查询 selectByProjectId
     * @param projectId 项目id
     * @return 同一项目的脚本信息结果集
     */
    public List<AnalysisScript> selectByProjectId(Long projectId);


    /**
     * 查询【请填写功能名称】列表
     * 
     * @param analysisScript 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AnalysisScript> selectAnalysisScriptList(AnalysisScript analysisScript);

    /**
     * 新增【请填写功能名称】
     * 
     * @param analysisScript 【请填写功能名称】
     * @return 结果
     */
    public int insertAnalysisScript(AnalysisScript analysisScript);

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisScript 【请填写功能名称】
     * @return 结果
     */
    public int updateAnalysisScript(AnalysisScript analysisScript);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisScriptById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnalysisScriptByIds(Long[] ids);
}
