package com.zhangjian.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjian.music.po.SingerPO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SingerMapper extends BaseMapper<SingerPO> {
}
