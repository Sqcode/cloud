package sqc.user.entity;

import sqc.entity.po.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Sqcode
 * @since 2021-04-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="User对象", description="用户")
public class User extends BasePO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "是否删除，1-删除/0-未删除")
    private Boolean flag;

    @ApiModelProperty(value = "1 超级管理员，2超级，3....")
    private Integer type;


}
