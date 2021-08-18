package sqc.quartz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quartz implements Serializable {
    private String id;

    private String jobName;

    private String description;

    private String cronExpression;

    private String expressionDesc;

    private String beanClass;

    private String jobStatus;

    private String jobGroup;

    private Boolean flag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

}