package com.ruoyi.analysis.service;

import com.ruoyi.analysis.domain.AnalysisProject;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IAnalysisProjectService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public AnalysisProject selectAnalysisProjectById(Long id);

    /**
     * 查询 日志分析项目信息 列表
     *
     * @param analysisProject 筛选内容
     * @return 日志分析项目信息 集合
     */
    public List<AnalysisProject> selectAnalysisProjectList(AnalysisProject analysisProject);

    /**
     * 新增 日志分析项目信息
     * 
     * @param analysisProject 日志分析项目信息
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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisProjectByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteAnalysisProjectById(Long id);
}
