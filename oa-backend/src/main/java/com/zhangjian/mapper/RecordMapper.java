package com.zhangjian.mapper;

import com.zhangjian.de.po.RecordPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RecordMapper {

    /**
     * 插入数据记录
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into record(operate, create_time) values (#{operate}, #{createTime})")
    Integer insert(RecordPO recordPO);
}
