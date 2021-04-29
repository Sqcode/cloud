package sqc.user.service.impl;

import sqc.user.entity.User;
import sqc.user.mapper.UserMapper;
import sqc.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Sqcode
 * @since 2021-04-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
