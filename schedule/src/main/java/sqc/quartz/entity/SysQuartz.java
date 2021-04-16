package sqc.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysQuartz {

    private Integer id;

    private String jobName;

    private String description;

    private String cronExpression;

    private String expressionDesc;

    private String beanClass;

    private String jobStatus;

    private String jobGroup;

    private Integer flag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

}