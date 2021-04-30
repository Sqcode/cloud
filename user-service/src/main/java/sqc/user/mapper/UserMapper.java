package sqc.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import sqc.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author Sqcode
 * @since 2021-04-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
