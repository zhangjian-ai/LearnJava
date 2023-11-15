package com.zhangjian.mapper;

import com.zhangjian.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 一个User表示一行user数据，也就表示一个user对象
     * @return
     */
    @Select("select * from user")
    public List<User> listUser();
}
