package sqc.quartz.rest;

import com.alibaba.nacos.common.utils.UuidUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sqc.quartz.entity.SysQuartz;
import sqc.quartz.enums.QuartzJobOperateEnum;
import sqc.quartz.job.ExampleJob;
import sqc.quartz.service.QuartzManagerService;

import java.util.Date;

@RestController
//@Api(tags = "任务管理")
@RequestMapping(value = "quartz", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuartzJobController {

    @Autowired
    private QuartzManagerService service;

    @PostMapping
    //@ApiOperation(value = "新增任务")
    public void addJob(@RequestBody SysQuartz job) throws ClassNotFoundException, SchedulerException {
        checkService();
        service.addJob(job);
    }

    private void checkService() {
        if (service == null) {
            throw new RuntimeException("当前服务没有开启任务管理模式");
        }
    }

    @PutMapping("{id}")
    //@ApiOperation(value = "修改任务", notes = "operateEnum值：stop, resume, update")
    public void modifyJob(@PathVariable Integer id, @RequestBody SysQuartz job, QuartzJobOperateEnum operateEnum) throws SchedulerException {
        checkService();
        service.modifyJob(id, operateEnum, job);
    }

    @DeleteMapping("{id}")
    //@ApiOperation(value = "删除任务")
    public void removeJob(@PathVariable Integer id) throws SchedulerException {
        checkService();
        service.removeJob(id);
    }

    @GetMapping("test")
//    @ApiOperation(value = "测试增加任务")
    public void test(SysQuartz query) throws SchedulerException, ClassNotFoundException {
        System.out.println("test");
        SysQuartz job = new SysQuartz();
        job.setBeanClass(ExampleJob.class.getName());
        job.setCronExpression("0/1 * * * * ?");
        job.setJobName("test");
        job.setJobGroup(UuidUtils.generateUuid());
        job.setDescription("修改描述");
        job.setCreatedTime(new Date());
        job.setCreatedBy("Sqc");
        job.setUpdatedBy("Sqc");
        service.addJob(job);
    }

}
