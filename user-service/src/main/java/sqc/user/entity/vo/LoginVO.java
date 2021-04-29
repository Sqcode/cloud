package sqc.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Sqcode
 * @Date: 2021/4/29 8:10
 */
@Data
@ApiModel(value = "登录返回")
public class LoginVO {
    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "登陆名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户类型")
    private Integer type;

    @ApiModelProperty(value = "用户类型名称")
    private String typeName;

    @ApiModelProperty(value = "token")
    private String token;

}
