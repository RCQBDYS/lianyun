package com.ruoyi.web.controller.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.ruoyi.analysis.domain.AnalysisResult;
import com.ruoyi.analysis.domain.AnalysisScript;
import com.ruoyi.analysis.service.IAnalysisResultService;
import com.ruoyi.analysis.service.IAnalysisScriptService;
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
import com.ruoyi.analysis.domain.AnalysisLog;
import com.ruoyi.analysis.service.IAnalysisLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-10-30
 */
@RestController
@RequestMapping("/analysis/log")
public class AnalysisLogController extends BaseController
{
    @Autowired
    private IAnalysisLogService analysisLogService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IAnalysisResultService analysisResultService;

    @Autowired
    private IAnalysisScriptService analysisScriptService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnalysisLog analysisLog)
    {
        startPage();
        List<AnalysisLog> list = analysisLogService.selectAnalysisLogList(analysisLog);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AnalysisLog analysisLog)
    {
        List<AnalysisLog> list = analysisLogService.selectAnalysisLogList(analysisLog);
        ExcelUtil<AnalysisLog> util = new ExcelUtil<AnalysisLog>(AnalysisLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") Long logId)
    {
        return AjaxResult.success(analysisLogService.selectAnalysisLogById(logId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【新增log文件】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(MultipartFile file, @RequestParam(value = "scriptId")long scriptId) throws IOException, InterruptedException {
        if (!file.isEmpty()){
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//            根据获取到的文件进行对应路径文件的上传
            String filePath = FileUploadUtils.upload(RuoYiConfig.getLogPath(),file);
            String fileName = file.getOriginalFilename();
            AnalysisLog analysisLog = new AnalysisLog();
            analysisLog.setLogName(fileName);
            analysisLog.setLogPath(filePath);
            analysisLog.setLogUploader(loginUser.getUsername());
            analysisLog.setScriptId(scriptId);

            if (analysisLogService.insertAnalysisLog(analysisLog) == 1){
//                获取最新生成的logId，确认是否analysis是最新插入数据的全部信息
                String logFileName = analysisLog.getLogName();
                String resultOriginalName = logFileName.substring(0,logFileName.indexOf('.'));
//        获取logId文件的路径信息
              String logPath = analysisLog.getLogPath();
                int index = logPath.length();
                String logPathRoot = RuoYiConfig.getProfile() + logPath.substring(8,index);
                System.out.println("logPathRoot = " + logPathRoot );
//        获取对应logId的脚本信息
                AnalysisScript script = analysisScriptService.selectAnalysisScriptById(analysisLog.getScriptId());
                String scriptPath = script.getScriptPath();
                int index1 = scriptPath.length();
                String scriptPathRoot = RuoYiConfig.getProfile() + scriptPath.substring(8,index1);
                System.out.println("scriptPathRoot  = " + scriptPathRoot);
                String resultDirPath = RuoYiConfig.getLogResultPath();
                System.out.println("resultDirPath = " + resultDirPath);
//                创建日志分析结果文件夹
                File root = new File(resultDirPath);
                if (!root.exists()){
                    if(root.mkdir()){
                        System.out.println("log文件解析结果目录创建成功");
                    } else{
                        System.out.println("log文件解析结果目录创建失败");
                    }
                }
                System.out.println("--------------------python 脚本运行 ------------------------------------------------");
                String[] args = new String[]{"python",scriptPathRoot,logPathRoot,resultOriginalName};
                String resultLine = null;
                try{
                    Process process = Runtime.getRuntime().exec(args);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;
                    while ((line = bufferedReader.readLine())!= null){
                        resultLine = line;
                        System.out.println("输出 ："+line);
                    }
                    bufferedReader.close();
                    int code = process.waitFor();
                    System.out.println("脚本执行结果" + code);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //                将获取到的全部信息放入analysisResult 结果集中
                AnalysisResult analysisResult = new AnalysisResult();
                analysisResult.setResultName(resultOriginalName);
                analysisResult.setScriptId(scriptId);

                if (resultLine == null){
                    System.out.println("脚本解析失败");
                }else{
                    analysisResult.setResultPath(resultLine);
                }

                analysisResultService.insertAnalysisResult(analysisResult);
                return AjaxResult.success("log上传成功");
            } else {
                return AjaxResult.error("log上传失败");
            }
        } else {
            return AjaxResult.error("未添加log文件");
        }
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/updateLog")
    public AjaxResult edit(MultipartFile file,@RequestParam(value = "logId")long logId) throws IOException {
        if (!file.isEmpty()){
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//            根据获取到的文件进行对应路径文件的上传
            String filePath = FileUploadUtils.upload(RuoYiConfig.getLogPath(),file);
            String fileName = file.getOriginalFilename();
            AnalysisLog analysisLog = new AnalysisLog();
            analysisLog.setLogName(fileName);
            analysisLog.setLogPath(filePath);
            analysisLog.setLogUploader(loginUser.getUsername());
            analysisLog.setLogId(logId);


            if (analysisLogService.updateAnalysisLog(analysisLog) == 1){
                AnalysisLog log = analysisLogService.selectAnalysisLogById(logId);
                long scriptId = log.getScriptId();
//                获取log文件的名称，需要去掉“.”
                String logFileName = log.getLogName();
                String resultOriginalName = logFileName.substring(0,logFileName.indexOf('.'));
//                获取log的直接路径
                String logPath = log.getLogPath();
                int index = logPath.length();
                String logPathRoot = RuoYiConfig.getProfile() + logPath.substring(8,index);
                System.out.println("logPathRoot = " + logPathRoot );
//        获取对应logId的脚本信息
                AnalysisScript script = analysisScriptService.selectAnalysisScriptById(scriptId);
                String scriptPath = script.getScriptPath();
                int index1 = scriptPath.length();
                String scriptPathRoot = RuoYiConfig.getProfile() + scriptPath.substring(8,index1);
                System.out.println("scriptPathRoot  = " + scriptPathRoot);
                String resultDirPath = RuoYiConfig.getLogResultPath();
                System.out.println("resultDirPath = " + resultDirPath);

                System.out.println("--------------------python 脚本运行 ------------------------------------------------");
                String[] args = new String[]{"python",scriptPathRoot,logPathRoot,resultOriginalName};
                String resultLine = null;
                try{
                    Process process = Runtime.getRuntime().exec(args);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;
                    while ((line = bufferedReader.readLine())!= null){
                        resultLine = line;
                        System.out.println("输出 ："+line);
                    }
                    bufferedReader.close();
                    int code = process.waitFor();
                    System.out.println("脚本执行结果" + code);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //                将获取到的全部信息放入analysisResult 结果集中
                AnalysisResult analysisResult = new AnalysisResult();
                analysisResult.setResultName(resultOriginalName);
                analysisResult.setScriptId(scriptId);

                if (resultLine == null){
                    System.out.println("脚本解析失败");
                }else{
                    analysisResult.setResultPath(resultLine);
                }

                analysisResultService.updateAnalysisResult(analysisResult);

                return AjaxResult.success("log修改成功");
            } else {
                return AjaxResult.error("log修改失败");
            }
        } else {
            return AjaxResult.error("未添加log文件");
        }
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{logIds}")
    public AjaxResult remove(@PathVariable Long[] logIds)
    {
        return toAjax(analysisLogService.deleteAnalysisLogByIds(logIds));
    }
}
