package com.zhangjian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjian.de.dto.UserDto;
import com.zhangjian.de.po.UserPo;
import com.zhangjian.de.vo.PageResultVo;
import com.zhangjian.de.vo.UserVo;

import java.time.LocalDate;
import java.util.List;

/**
 * 创建业务接口 UserService。继承 IService 并指定泛型为 UserPo
 */
public interface UserService extends IService<UserPo> {

    void deductMoneyById(Integer id, Integer money);

    List<UserPo> listByConditions(String username, Byte gender, LocalDate start, LocalDate end);

    UserVo queryUserAndAddress(Integer id);

    List<UserVo> queryUserAndAddressByIds(List<Integer> ids);

    PageResultVo<UserVo> queryUsersByPage(UserDto userDto);
}
