package sqc.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sqc.entity.vo.Result;
import sqc.user.entity.form.LoginForm;
import sqc.user.entity.vo.LoginVO;
import sqc.user.service.LoginService;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author Sqcode
 * @since 2021-04-29
 */
@Api(value = "login",tags = "登录")
@RestController
@RequestMapping("/user/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping(value = "pwd")
    @ApiOperation(value = "账号密码登录接口")
    public Result<LoginVO> login(@RequestBody LoginForm loginForm){

        LoginVO vo = loginService.login(loginForm);
        return Result.success(vo);
    }
}

