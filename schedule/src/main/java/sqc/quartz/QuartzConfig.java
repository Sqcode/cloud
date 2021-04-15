package sqc.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import sqc.quartz.job.DemoJob;

@Configuration
public class QuartzConfig {

    /**
     * 1、创建Job对象
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(DemoJob.class);// 关联我们自己的Job类
        return factoryBean;
    }

    /**
     * 2、创建Trigger对象
     */
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        // 关联JobDetail对象
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        // 该参数表示一个执行的毫秒数
        factoryBean.setRepeatInterval(1000); // 每隔1秒执行一次
        // 重复次数 （1：额外+1，即会执行2次。-1：一直执行。0：不执行），
        factoryBean.setRepeatCount(-1);
        return factoryBean;
    }

    /**
     * 3、创建Scheduler
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        // 关联trigger
        factoryBean.setTriggers(simpleTriggerFactoryBean.getObject());
        return factoryBean;
    }

}
