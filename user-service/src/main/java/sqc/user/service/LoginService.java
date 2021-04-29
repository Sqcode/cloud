package sqc.user.service;

import sqc.user.entity.form.LoginForm;
import sqc.user.entity.vo.LoginVO;

/**
 * @Description:
 * @Author: Sqcode
 * @Date: 2021/4/29 8:09
 */
public interface LoginService {

    LoginVO login(LoginForm loginForm);
}
