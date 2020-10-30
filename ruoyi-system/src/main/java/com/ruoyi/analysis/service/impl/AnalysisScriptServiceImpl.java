package com.ruoyi.analysis.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.analysis.mapper.AnalysisScriptMapper;
import com.ruoyi.analysis.domain.AnalysisScript;
import com.ruoyi.analysis.service.IAnalysisScriptService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-26
 */
@Service
public class AnalysisScriptServiceImpl implements IAnalysisScriptService 
{
    @Autowired
    private AnalysisScriptMapper analysisScriptMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnalysisScript selectAnalysisScriptById(Long id)
    {
        return analysisScriptMapper.selectAnalysisScriptById(id);
    }

    /**
     *查询 selectByProjectId
     * @param projectId 项目id
     * @return 同一项目脚本信息结果集
     */

    @Override
    public List<AnalysisScript> selectByProjectId(Long projectId) {
        return analysisScriptMapper.selectByProjectId(projectId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param analysisScript 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnalysisScript> selectAnalysisScriptList(AnalysisScript analysisScript)
    {
        return analysisScriptMapper.selectAnalysisScriptList(analysisScript);
    }

    /**
     * 新增 脚本信息
     * 
     * @param analysisScript 新增脚本信息
     * @return 结果
     */
    @Override
    public int insertAnalysisScript(AnalysisScript analysisScript)
    {
        analysisScript.setCreateTime(DateUtils.getNowDate());
        return analysisScriptMapper.insertAnalysisScript(analysisScript);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisScript 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnalysisScript(AnalysisScript analysisScript)
    {
        analysisScript.setCreateTime(DateUtils.getNowDate());
        return analysisScriptMapper.updateAnalysisScript(analysisScript);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisScriptByIds(Long[] ids)
    {
        return analysisScriptMapper.deleteAnalysisScriptByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisScriptById(Long id)
    {
        return analysisScriptMapper.deleteAnalysisScriptById(id);
    }
}
