package sqc.goods.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import sqc.util.UserContextHolder;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Description: MybatisPlusMeta 自动填充
 * @Author: Sqcode
 * @Date: 2021/4/30 9:49
 */
@Configuration
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdBy", UserContextHolder.getInstance().getUserId(), metaObject);
        this.setFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
//        this.setFieldValByName("version", Long.valueOf(1),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedBy", UserContextHolder.getInstance().getUserId(), metaObject);
        this.setFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}
