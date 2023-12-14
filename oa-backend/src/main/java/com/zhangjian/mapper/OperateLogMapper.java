package com.zhangjian.mapper;

import com.zhangjian.de.po.OperateLogPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    /**
     * 新增数据
     * @param operateLogPO
     */
    void insert(OperateLogPO operateLogPO);
}
