package com.ruoyi.web.controller.analysis;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.analysis.domain.AnalysisScript;
import com.ruoyi.analysis.service.IAnalysisScriptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-10-26
 */
@RestController
@RequestMapping("/analysis/script")
public class AnalysisScriptController extends BaseController
{
    @Autowired
    private IAnalysisScriptService analysisScriptService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:script:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnalysisScript analysisScript)
    {
        startPage();
        List<AnalysisScript> list = analysisScriptService.selectAnalysisScriptList(analysisScript);
        return getDataTable(list);
    }

    /**
     *根据 projectId查询脚本信息
     */
    @GetMapping(value = "/projectId/{projectId}")
    public AjaxResult selectByProjectId(@PathVariable("projectId") String projectId){
        return AjaxResult.success(analysisScriptService.selectByProjectId(Long.parseLong(projectId)));
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:script:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AnalysisScript analysisScript)
    {
        List<AnalysisScript> list = analysisScriptService.selectAnalysisScriptList(analysisScript);
        ExcelUtil<AnalysisScript> util = new ExcelUtil<AnalysisScript>(AnalysisScript.class);
        return util.exportExcel(list, "script");
    }

    /**
     * 获取【对应scriptId脚本的】详细信息
     */
    @GetMapping(value = "/{scriptId}")
    public AjaxResult getInfo(@PathVariable("scriptId") Long scriptId)
    {
        return AjaxResult.success(analysisScriptService.selectAnalysisScriptById(scriptId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:script:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnalysisScript analysisScript)
    {
        return toAjax(analysisScriptService.insertAnalysisScript(analysisScript));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【脚本修改】", businessType = BusinessType.UPDATE)
    @PostMapping("/updateScript")
    public AjaxResult edit(MultipartFile file,@RequestParam(value = "id")long id) throws IOException {
        if (!file.isEmpty()){
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//            根据获取到的文件进行对应路径文件的上传
            String filePath = FileUploadUtils.upload(RuoYiConfig.getUploadScriptPath(),file);
            String fileName = file.getOriginalFilename();
            AnalysisScript analysisScript = new AnalysisScript();
            analysisScript.setId(id);
            analysisScript.setCreater(loginUser.getUsername());
            analysisScript.setScriptPath(filePath);
            analysisScript.setScriptName(fileName);
            if (analysisScriptService.updateAnalysisScript(analysisScript) == 1){
                return AjaxResult.success("脚本修改成功");
            } else {
                return AjaxResult.error("脚本修改失败");
            }
        } else {
            return AjaxResult.error("未添加脚本");
        }
//        return toAjax(analysisScriptService.updateAnalysisScript(analysisScript));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【删除脚本信息】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scriptIds}")
    public AjaxResult remove(@PathVariable Long[] scriptIds)
    {
        return toAjax(analysisScriptService.deleteAnalysisScriptByIds(scriptIds));
    }

    /**
     *导入脚本信息
     */
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, @RequestParam(value = "projectId")long projectId) throws Exception
    {
        if (!file.isEmpty()){
//            根据token值获取用户名称
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//            根据获取到的文件进行对应路径文件的上传
            String filePath = FileUploadUtils.upload(RuoYiConfig.getUploadScriptPath(),file);
            String fileName = file.getOriginalFilename();
            AnalysisScript analysisScript = new AnalysisScript();
            analysisScript.setCreater(loginUser.getUsername());
            analysisScript.setProjectId(projectId);
            analysisScript.setScriptPath(filePath);
            analysisScript.setScriptName(fileName);
//            脚本信息的插入
            if (analysisScriptService.insertAnalysisScript(analysisScript) == 1){
                return AjaxResult.success("脚本上传成功");
            } else{
                return AjaxResult.error("脚本上传失败");
            }
        } else {
            return AjaxResult.error("未添加脚本文件！");
        }

    }

}
