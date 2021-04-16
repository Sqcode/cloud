package sqc.quartz.task;

import java.util.Date;

//@Component
public class TestTask {

//    @Scheduled(cron="0/1 * * * * ?")
    private void task () {
        System.out.println(new Date());
    }

}
