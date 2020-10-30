package com.ruoyi.analysis.mapper;

import com.ruoyi.analysis.domain.AnalysisProject;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface AnalysisProjectMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnalysisProject selectAnalysisProjectById(Long id);

    /**
     * 查询 日志分析项目信息
     * 
     * @param analysisProject 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AnalysisProject> selectAnalysisProjectList(AnalysisProject analysisProject);

    /**
     * 新增 日志分析项目信息
     * 
     * @param analysisProject 项目信息
     * @return 结果
     */
    public int insertAnalysisProject(AnalysisProject analysisProject);

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisProject 【请填写功能名称】
     * @return 结果
     */
    public int updateAnalysisProject(AnalysisProject analysisProject);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisProjectById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAnalysisProjectByIds(Long[] ids);
}
