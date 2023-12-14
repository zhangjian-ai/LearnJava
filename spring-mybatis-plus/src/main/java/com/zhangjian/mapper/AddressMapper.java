package com.zhangjian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjian.de.po.AddressPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper extends BaseMapper<AddressPo> {
}
