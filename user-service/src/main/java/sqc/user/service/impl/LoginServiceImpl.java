package sqc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sqc.enums.UserErrorTypeEnum;
import sqc.user.entity.User;
import sqc.user.entity.form.LoginForm;
import sqc.user.entity.vo.LoginVO;
import sqc.user.exception.UserException;
import sqc.user.service.IUserService;
import sqc.user.service.LoginService;

/**
 * @Description:
 * @author: Sqcode
 * @since: 2021/4/29 8:17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private IUserService userService;

    @Override
    public LoginVO login(LoginForm loginForm) {
        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginForm.getUserName());
        queryWrapper.eq("password", loginForm.getPassword());
        User user = userService.getOne(queryWrapper);
        //账号密码错误
        if (user == null) {
            log.debug("loginForm: {}", loginForm);
            throw new UserException(UserErrorTypeEnum.A0210);
        } else {
            //判断是否被禁用
//            if (user.getIsEnable().equals(CommonConstant.WEATHER_FALSE)) {
//                throw new BusinessException(BusinessEnum.USER_ENABLE);
//            } else {
//                //登录成功
//                //生成token
//                Random random = new Random();
//                String token = MD5Utils.ecodeByMD5(user.getUserName() + user.getPassword() + random.nextInt(1000));
//                //redis缓存用户数据
//                User user = new User();
//                user.setId(user.getId());
//                user.setUserName(user.getUserName());
//                user.setRoleId(user.getRoleId());
//                //查询角色信息
//                SysRole sysRole = sysRoleService.getById(user.getRoleId());
//                if (sysRole == null) {
//                    throw new BusinessException(BusinessEnum.ROLE_LOGIN_NO);
//                }
//                redissonUtils.setString(token, JSON.toJSONString(user),24, TimeUnit.HOURS);
//
//                //返回数据
//                LoginResponse response = new LoginResponse();
//                response.setId(user.getId());
//                response.setUserName(user.getUserName());
//                response.setRoleType(sysRole.getRoleType());
//                response.setRoleName(sysRole.getRoleName());
//                response.setToken(token);
//                response.setIsSendOpen(user.getIsSendOpen());
//                response.setSendPayType(user.getSendPayType());
//                return response;
//            }
        }
        return null;
    }
}
