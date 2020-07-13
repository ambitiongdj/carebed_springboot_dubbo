package com.carebed.quartz.job.manage;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.carebed.common.constant.ScheduleConstants;
import com.carebed.exception.job.TaskException;
import com.carebed.manage.service.ISysJobService;
import com.carebed.quartz.entity.SysJob;
import com.carebed.quartz.util.ScheduleUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/job/manage")
public class JobManageController {

    private static final Logger log = LoggerFactory.getLogger(JobManageController.class);


    @Autowired
    private Scheduler scheduler;

    @Reference
    private ISysJobService jobService;

    /**
     * 项目启动时，初始化定时器
     主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void initJobTask() throws SchedulerException, TaskException
    {
        log.info("项目启动时，初始化定时器----start");
        scheduler.clear();
        List<SysJob> jobList = jobService.initJobTask();
        for (SysJob job : jobList)
        {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        log.info("项目启动时，初始化定时器----end");
    }

    /**
     * 暂停任务
     * @param json
     */
    @RequestMapping("/pauseJob")
    @ResponseBody
    public String pauseJob(@RequestBody String json){
        log.info("暂停任务--json=" + json);
        JSONObject jsonObject= JSONObject.parseObject(json);
        SysJob job = (SysJob)JSONObject.toJavaObject(jsonObject, SysJob.class);
        try {
            scheduler.pauseJob(ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 恢复任务
     * @param json
     */
    @RequestMapping("/resumeJob")
    @ResponseBody
    public String resumeJob(@RequestBody String json){
        log.info("恢复任务--json=" + json);
        JSONObject jsonObject= JSONObject.parseObject(json);
        SysJob job = (SysJob)JSONObject.toJavaObject(jsonObject, SysJob.class);
        try {
            scheduler.resumeJob(ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     * @param json
     */
    @RequestMapping("/deleteJob")
    @ResponseBody
    public String deleteJob(@RequestBody String json){
        log.info("删除任务--json=" + json);
        JSONObject jsonObject= JSONObject.parseObject(json);
        SysJob job = (SysJob)JSONObject.toJavaObject(jsonObject, SysJob.class);
        try {
            scheduler.deleteJob(ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 立即运行任务
     * @param json
     */
    @RequestMapping("/triggerJob")
    @ResponseBody
    public String triggerJob(@RequestBody String json){
        log.info("立即运行任务--json=" + json);
        JSONObject jsonObject= JSONObject.parseObject(json);
        SysJob job = (SysJob)JSONObject.toJavaObject(jsonObject, SysJob.class);
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleConstants.TASK_PROPERTIES, job);
            scheduler.triggerJob(ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup()), dataMap);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 新增任务
     * @param json
     */
    @RequestMapping("/createScheduleJob")
    @ResponseBody
    public String createScheduleJob(@RequestBody String json){
        log.info("新增任务--json=" + json);
        JSONObject jsonObject= JSONObject.parseObject(json);
        SysJob job = (SysJob)JSONObject.toJavaObject(jsonObject, SysJob.class);
        try {
            ScheduleUtils.createScheduleJob(scheduler, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (TaskException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 更新任务
     * @param json
     */
    @RequestMapping("/updateSchedulerJob")
    @ResponseBody
    public String updateSchedulerJob(@RequestBody String json){
        log.info("更新任务--json=" + json);
        JSONObject jsonObject= JSONObject.parseObject(json);
        SysJob job = (SysJob)JSONObject.toJavaObject(jsonObject, SysJob.class);
        try {
            // 判断是否存在
            JobKey jobKey = ScheduleUtils.getJobKey(job.getJobId(), job.getJobGroup());
            if (scheduler.checkExists(jobKey))
            {
                // 防止创建时存在数据问题 先移除，然后在执行创建操作
                scheduler.deleteJob(jobKey);
            }
            ScheduleUtils.createScheduleJob(scheduler, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (TaskException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
