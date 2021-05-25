package sqc.user.service;

import sqc.user.entity.form.LoginForm;
import sqc.user.entity.vo.LoginVO;

/**
 * @Description:
 * @author: Sqcode
 * @since: 2021/4/29 8:09
 */
public interface LoginService {

    LoginVO login(LoginForm loginForm);
}
