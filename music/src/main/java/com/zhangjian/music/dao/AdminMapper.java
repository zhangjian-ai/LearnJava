package com.zhangjian.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjian.music.po.AdminPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<AdminPO> {
}
