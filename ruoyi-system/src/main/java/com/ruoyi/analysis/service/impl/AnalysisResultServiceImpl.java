package com.ruoyi.analysis.service.impl;

import java.util.List;

import com.ruoyi.analysis.domain.AnalysisLog;
import com.ruoyi.analysis.domain.AnalysisScript;
import com.ruoyi.analysis.mapper.AnalysisLogMapper;
import com.ruoyi.analysis.mapper.AnalysisScriptMapper;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.analysis.mapper.AnalysisResultMapper;
import com.ruoyi.analysis.domain.AnalysisResult;
import com.ruoyi.analysis.service.IAnalysisResultService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-31
 */
@Service
public class AnalysisResultServiceImpl implements IAnalysisResultService 
{
    @Autowired
    private AnalysisResultMapper analysisResultMapper;

    @Autowired
    private AnalysisLogMapper analysisLogMapper;

    @Autowired
    private AnalysisScriptMapper analysisScriptMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param scriptId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public AnalysisResult selectAnalysisResultByScriptId(Long scriptId)
    {
        return analysisResultMapper.selectAnalysisResultByScriptId(scriptId);
    }

    @Override
    public AnalysisResult selectAnalysisResultById(Long resultId) {
        return analysisResultMapper.selectAnalysisResultById(resultId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param analysisResult 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnalysisResult> selectAnalysisResultList(AnalysisResult analysisResult)
    {
        return analysisResultMapper.selectAnalysisResultList(analysisResult);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param analysisResult 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnalysisResult(AnalysisResult analysisResult)
    {
        analysisResult.setResultCreattime(DateUtils.getNowDate());
        return analysisResultMapper.insertAnalysisResult(analysisResult);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param analysisResult 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnalysisResult(AnalysisResult analysisResult)
    {
        return analysisResultMapper.updateAnalysisResult(analysisResult);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param resultIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisResultByIds(Long[] resultIds)
    {
        return analysisResultMapper.deleteAnalysisResultByIds(resultIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param resultId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteAnalysisResultById(Long resultId)
    {
        return analysisResultMapper.deleteAnalysisResultById(resultId);
    }
}
