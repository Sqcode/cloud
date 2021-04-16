package sqc.quartz.dao;

import sqc.quartz.entity.SysQuartz;

public interface SysQuartzMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysQuartz record);

    int insertSelective(SysQuartz record);

    SysQuartz selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysQuartz record);

    int updateByPrimaryKey(SysQuartz record);
}