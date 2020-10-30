package com.ruoyi.web.controller.analysis;

import com.ruoyi.analysis.domain.AnalysisProject;
import com.ruoyi.analysis.service.IAnalysisProjectService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 672025
 * @date 14:24 2020/10/22
 * @description 日志分析中项目处理控制类
 */
@RestController
@RequestMapping("/analysis/project")
public class AnalysisProjectController extends BaseController {
    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IAnalysisProjectService analysisProjectService;


    /**
     * 根据测试角色查询用户信息
     */
    @GetMapping("/getTesterList")
    public AjaxResult getTestList() {
//        搜索测试人员角色id
        long roleId = iSysRoleService.selectByRoleName("测试人员").getRoleId();
        List<SysUser> sysUser = iSysUserService.selectByRoleId(roleId);
        return AjaxResult.success(sysUser);
    }

    /**
     * 根据开发角色查询用户信息
     */
    @GetMapping("/getDevelopList")
    public AjaxResult getDevelopList() {
        long roleId = iSysRoleService.selectByRoleName("开发人员").getRoleId();
        List<SysUser> sysUsers = iSysUserService.selectByRoleId(roleId);
        return AjaxResult.success(sysUsers);
    }

    /**
     * 新增日志分析项目信息
     */
    @PostMapping("/addProject")
    public AjaxResult addProject(@RequestBody AnalysisProject project){
        System.out.println(project.toString());
        int flag = analysisProjectService.insertAnalysisProject(project);
        if (flag == 1){
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 查询 日志分析项目信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AnalysisProject analysisProject)
    {
        System.out.println(analysisProject.toString());
        startPage();
        List<AnalysisProject> list = analysisProjectService.selectAnalysisProjectList(analysisProject);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @GetMapping("/export")
    public AjaxResult export(AnalysisProject analysisProject)
    {
        List<AnalysisProject> list = analysisProjectService.selectAnalysisProjectList(analysisProject);
        ExcelUtil<AnalysisProject> util = new ExcelUtil<AnalysisProject>(AnalysisProject.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(analysisProjectService.selectAnalysisProjectById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PostMapping
    public AjaxResult add(@RequestBody AnalysisProject analysisProject)
    {
        return toAjax(analysisProjectService.insertAnalysisProject(analysisProject));
    }

    /**
     * 修改 日志分析项目信息
     */
    @PutMapping
    public AjaxResult edit(@RequestBody AnalysisProject analysisProject)
    {
        return toAjax(analysisProjectService.updateAnalysisProject(analysisProject));
    }

    /**
     * 删除 日志分析项目详细信息
     */
    @Log(title = "【删除日志分析的详细信息】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(analysisProjectService.deleteAnalysisProjectByIds(ids));
    }

}
