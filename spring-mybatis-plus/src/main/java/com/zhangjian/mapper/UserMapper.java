package com.zhangjian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjian.de.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

    /**
     * 根据 id 扣减薪资
     * @param id
     * @param money
     */
    @Update("update emp set salary = salary - #{money} where id = #{id}")
    void deductMoneyById(@Param("id") Integer id, @Param("money") Integer money);
}
