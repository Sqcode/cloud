package sqc.quartz.util;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sqc.quartz.entity.Quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 计划任务管理
 */
@Component
public class QuartzManager {
    @Autowired
    private Scheduler scheduler;

    /**
     * 增加一个任务
     *
     * @param job 任务
     * @throws SchedulerException     调度异常
     * @throws ClassNotFoundException 类找不到
     */
    public void addJob(Quartz job) throws SchedulerException, ClassNotFoundException {
        //加载指定任务的实例class文件
        Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(job.getBeanClass()));
        // 任务名称和组构成任务key
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(job.getJobName(), job.getJobGroup()).build();
        // 定义调度触发规则
        // 使用cornTrigger规则
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())// 触发器key
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression())).startNow().build();
        // 把作业和触发器注册到任务调度中
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<Quartz> listJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<Quartz> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                Quartz job = new Quartz();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public List<Quartz> listRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<Quartz> jobList = new ArrayList<Quartz>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            Quartz job = new Quartz();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void pauseJob(Quartz job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void resumeJob(Quartz job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void deleteJob(Quartz job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即执行job
     *
     * @param job
     * @throws SchedulerException
     */
    public void runJobNow(Quartz job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     *
     * @param job
     * @throws SchedulerException
     */
    public void updateJobCron(Quartz job) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
