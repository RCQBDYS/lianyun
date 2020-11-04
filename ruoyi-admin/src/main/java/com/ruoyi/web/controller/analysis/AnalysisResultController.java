package com.ruoyi.web.controller.analysis;

import java.io.*;
import java.util.List;

import com.ruoyi.analysis.domain.AnalysisResult;
import com.ruoyi.common.utils.file.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.analysis.domain.AnalysisResult;
import com.ruoyi.analysis.service.IAnalysisResultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-10-31
 */
@RestController
@RequestMapping("/analysis/result")
public class AnalysisResultController extends BaseController
{
    @Autowired
    private IAnalysisResultService analysisResultService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:result:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnalysisResult analysisResult)
    {
        startPage();
        List<AnalysisResult> list = analysisResultService.selectAnalysisResultList(analysisResult);
        return getDataTable(list);
    }

    /**
     *下载logResult结果
     */
    @GetMapping("/download/{scriptId}")
    public void downloadResult(@PathVariable("scriptId") Long scriptId, HttpServletResponse response,HttpServletRequest request) throws IOException {
//        首先根据scriptId进行数据的查询
        System.out.println("downLoad = " + scriptId);
        AnalysisResult analysisResult = analysisResultService.selectAnalysisResultByScriptId(scriptId);
        String resultPath = analysisResult.getResultPath();
//        开始进行下载
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName=" + FileUtils.setFileDownloadHeader(request,analysisResult.getResultName()));
        //将文件转换成二进制文件
        FileUtils.writeBytes(resultPath,response.getOutputStream());
    }
    /**
     *查询 【根据scriptId查询result结果集】
     */
    @GetMapping("/getByScriptId/{scriptId}")
    public AjaxResult getByScriptId(@PathVariable(value = "scriptId")long scriptId){
        return AjaxResult.success(analysisResultService.selectAnalysisResultByScriptId(scriptId));
    }



    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:result:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AnalysisResult analysisResult)
    {
        List<AnalysisResult> list = analysisResultService.selectAnalysisResultList(analysisResult);
        ExcelUtil<AnalysisResult> util = new ExcelUtil<AnalysisResult>(AnalysisResult.class);
        return util.exportExcel(list, "result");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:result:query')")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Long resultId)
    {
        return AjaxResult.success(analysisResultService.selectAnalysisResultById(resultId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:result:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnalysisResult analysisResult)
    {
        return toAjax(analysisResultService.insertAnalysisResult(analysisResult));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:result:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnalysisResult analysisResult)
    {
        return toAjax(analysisResultService.updateAnalysisResult(analysisResult));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:result:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Long[] resultIds)
    {
        return toAjax(analysisResultService.deleteAnalysisResultByIds(resultIds));
    }
}
