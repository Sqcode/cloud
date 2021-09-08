package sqc.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;
import sqc.quartz.entity.Quartz;
import sqc.quartz.enums.QuartzJobOperateEnum;

public interface QuartzManagerService extends IService<Quartz> {

    void addJob(Quartz job) throws ClassNotFoundException, SchedulerException;

    void modifyJob(String id, Quartz job, QuartzJobOperateEnum operateEnum) throws SchedulerException;

    void removeJob(String id) throws SchedulerException;

}
