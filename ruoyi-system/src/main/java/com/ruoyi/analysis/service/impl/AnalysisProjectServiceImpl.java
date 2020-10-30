package com.ruoyi.analysis.service.impl;

import java.util.List;

import com.ruoyi.analysis.domain.AnalysisProject;
import com.ruoyi.analysis.mapper.AnalysisProjectMapper;
import com.ruoyi.analysis.service.IAnalysisProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 672025
 */
@Service
public class AnalysisProjectServiceImpl implements IAnalysisProjectService
{
    @Autowired
    private AnalysisProjectMapper analysisProjectMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnalysisProject selectAnalysisProjectById(Long id)
    {
        return analysisProjectMapper.selectAnalysisProjectById(id);
    }

    /**
     * 查询日志分析项目信息列表
     * 
     * @param analysisProject 筛选内容
     * @return 日志分析项目信息结果集
     */
    @Override
    public List<AnalysisProject> selectAnalysisProjectList(AnalysisProject analysisProject)
    {
        return analysisProjectMapper.selectAnalysisProjectList(analysisProject);
    }

    /**
     * 新增 日志分析项目信息
     * 
     * @param analysisProject 日志分析项目信息
     * @return 结果
     */
    @Override
    public int insertAnalysisProject(AnalysisProject analysisProject)
    {
        return analysisProjectMapper.insertAnalysisProject(analysisProject);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnalysisProject(AnalysisProject analysisProject)
    {
        return analysisProjectMapper.updateAnalysisProject(analysisProject);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisProjectByIds(Long[] ids)
    {
        return analysisProjectMapper.deleteAnalysisProjectByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisProjectById(Long id)
    {
        return analysisProjectMapper.deleteAnalysisProjectById(id);
    }
}
