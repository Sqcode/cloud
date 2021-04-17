package sqc.quartz.rest;

import com.alibaba.nacos.common.utils.UuidUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sqc.quartz.entity.Quartz;
import sqc.quartz.enums.QuartzJobOperateEnum;
import sqc.quartz.job.ExampleJob;
import sqc.quartz.service.QuartzManagerService;

@RestController
//@Api(tags = "任务管理")
@RequestMapping(value = "quartz", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuartzJobController {

    @Autowired
    private QuartzManagerService service;

    @PostMapping
    //@ApiOperation(value = "新增任务")
    public void addJob(@RequestBody Quartz job) throws ClassNotFoundException, SchedulerException {
        checkService();
        service.addJob(job);
    }

    private void checkService() {
        if (service == null) {
            throw new RuntimeException("当前服务没有开启任务管理模式");
        }
    }

    @PutMapping("{id}/{operateEnum}")
    //@ApiOperation(value = "修改任务", notes = "operateEnum值：stop, resume, update")
    public void modifyJob(@PathVariable String id, @RequestBody Quartz job, @PathVariable QuartzJobOperateEnum operateEnum) throws SchedulerException {
        checkService();
        service.modifyJob(id, job, operateEnum);
    }

    @DeleteMapping("{id}")
    //@ApiOperation(value = "删除任务")
    public void removeJob(@PathVariable String id) throws SchedulerException {
        checkService();
        service.removeJob(id);
    }

    @GetMapping("test")
//    @ApiOperation(value = "测试增加任务")
    public void test(Quartz query) throws SchedulerException, ClassNotFoundException {
        System.out.println("test");
        Quartz job = new Quartz();
        job.setBeanClass(ExampleJob.class.getName());
        job.setCronExpression("0/2 * * * * ?");
        job.setJobName("test");
        job.setJobStatus("1");
        job.setJobGroup(UuidUtils.generateUuid());
        job.setDescription("修改描述");
        job.setCreatedBy("Sqc");
        job.setUpdatedBy("Sqc");
        service.addJob(job);
    }

}
