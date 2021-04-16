package sqc.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sqc.quartz.entity.SysQuartz;

import java.util.List;

@Mapper
public interface SysQuartzMapper extends BaseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SysQuartz record);

    int insertSelective(SysQuartz record);

    SysQuartz selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysQuartz record);

    int updateByPrimaryKey(SysQuartz record);

    List<SysQuartz> selectJob();

    List<SysQuartz> selectJobByGroup(@Param("report") String report);

    SysQuartz selectByJobName(SysQuartz job);
}