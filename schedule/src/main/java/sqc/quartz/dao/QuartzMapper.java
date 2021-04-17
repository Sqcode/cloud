package sqc.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sqc.quartz.entity.Quartz;
@Mapper
public interface QuartzMapper extends BaseMapper<Quartz> {

    Quartz selectByJobName(Quartz job);
}