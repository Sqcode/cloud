package sqc.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePO implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableLogic
    private Integer flag;

    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.UPDATE)
    private String updatedBy;

    @TableField(fill = FieldFill.UPDATE)
    private Date updatedTime;

}
