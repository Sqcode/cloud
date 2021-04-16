package sqc.quartz.service;

import org.quartz.SchedulerException;
import sqc.quartz.entity.SysQuartz;
import sqc.quartz.enums.QuartzJobOperateEnum;

public interface QuartzManagerService {

    void addJob(SysQuartz job) throws ClassNotFoundException, SchedulerException;

    void modifyJob(Integer id, QuartzJobOperateEnum operateEnum, SysQuartz job) throws SchedulerException;

    void removeJob(Integer id) throws SchedulerException;

}
