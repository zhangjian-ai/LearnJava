package com.zhangjian.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.zhangjian.de.dto.UserDto;
import com.zhangjian.de.po.AddressPo;
import com.zhangjian.de.vo.AddressVo;
import com.zhangjian.de.vo.PageResultVo;
import com.zhangjian.de.vo.UserVo;
import com.zhangjian.mapper.UserMapper;
import com.zhangjian.de.po.UserPo;
import com.zhangjian.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 实现类实现 UserService，要实现接口所有的抽象方法
 * MP还提供了ServiceImpl实现类，已经默认实现了 IService 接口所有的抽象方法。业务实现类直接继承即可
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

    @Override
    @Transactional
    public void deductMoneyById(Integer id, Integer money) {
        // 1. 查询用户是否存在
        UserPo userPo = getById(id);

        if (userPo == null) {
            throw new RuntimeException("用户不存在，用户ID: " + id);
        }

        // 2. 检查salary是否充足，不能使salary为负 或 为零
        if (userPo.getSalary() <= money) {
            throw new RuntimeException("用户余额不足，不能扣减");
        }

        // 3. 扣减余额。ServiceImpl 中已经有属性 baseMapper，其类型就是 泛型指定的 UserMapper
        // baseMapper.deductMoneyById(id, money);

        // 使用 lambdaUpdate 处理，就不需要再实现 mapper
        // 其实这里还有线程安全问题。此处使用 事务 + 乐观锁 处理
        int diff = userPo.getSalary() - money;

        this.lambdaUpdate()
                .set(diff > 0, UserPo::getSalary, diff)
                .eq(UserPo::getSalary, userPo.getSalary()) // 乐观锁。执行更新时再次检查当前用户的薪资版本是否没有发生变更
                .eq(UserPo::getId, id)
                .update();
    }

    @Override
    public List<UserPo> listByConditions(String username, Byte gender, LocalDate start, LocalDate end) {

        return this.lambdaQuery()
                .like(username != null, UserPo::getUsername, username) // 字段不为空时，才会某个字段的构造条件
                .eq(gender != null, UserPo::getGender, gender)
                .ge(start != null, UserPo::getEntryDate, start)
                .le(end != null, UserPo::getEntryDate, end)
                .list(); // 这一步 执行查询。前面都是在构造查询条件
    }

    @Override
    public UserVo queryUserAndAddress(Integer id) {
        // 1. 查询用户
        UserPo userPo = this.getById(id);

        if (userPo == null) {
            throw new RuntimeException("用户不存在，id: " + id);
        }

        // 2. 使用 Db静态工具 直接查询用户的地址。这里用户和地址是一对多，因此查询使用 list
        List<AddressPo> addressPos = Db.lambdaQuery(AddressPo.class).eq(AddressPo::getUserId, id).list();

        // 3. 将PO转为VO，将 地址信息也添加到user中
        UserVo userVo = BeanUtil.copyProperties(userPo, UserVo.class); // BeanUtil  糊涂工具中的 bean工具类

        if (CollUtil.isNotEmpty(addressPos)) { // CollUtil  糊涂工具中的 集合工具类
            userVo.setAddresses(BeanUtil.copyToList(addressPos, AddressVo.class));
        }

        return userVo;
    }

    @Override
    public List<UserVo> queryUserAndAddressByIds(List<Integer> ids) {
        // 1. 查询出所有用户
        List<UserPo> userPoList = this.listByIds(ids);

        if (CollUtil.isEmpty(userPoList)) {
            throw new RuntimeException("无效的id集合");
        }

        // 2. 可以直接使用 ids查询地址，这里演示一个语法，从查询出的用户中，获取 id
        //    map 接受一个函数，将 UserPo 转成，UserId；
        //    collect 接受一个 Collector的实现类，这里直接使用已有的方法，toList 返回的是将 item 收集到列表的 Collector 实例
        List<Integer> userIds = userPoList.stream().map(UserPo::getId).collect(Collectors.toList());

        // 3. 查询所有用户的地址信息，每个人还会有多个地址
        List<AddressPo> addressPoList = Db.lambdaQuery(AddressPo.class).in(AddressPo::getUserId, userIds).list();

        // 4. 此时地址信息是所有用户的地址信息，这里按用户id，将地址信息分组
        Map<Integer, List<AddressPo>> userAddressList = new HashMap<>();
        if (CollUtil.isNotEmpty(addressPoList)) {
            //    groupingBy 返回的是一个实现了分组操作的 Collector 实例
            userAddressList = addressPoList.stream().collect(Collectors.groupingBy(AddressPo::getUserId));
        }

        // 5. 将用户PO转成VO，并将 地址信息也添加到user中
        ArrayList<UserVo> userVos = new ArrayList<>(userPoList.size()); // 当明确知道列表大小时，初始化时指定大小，可以避免后续触发扩容
        for (UserPo userPo : userPoList) {
            UserVo userVo = BeanUtil.copyProperties(userPo, UserVo.class);
            if (userAddressList.containsKey(userVo.getId())) { // 不是所有用户都一定有收货地址
                List<AddressVo> addressVos = BeanUtil.copyToList(userAddressList.get(userVo.getId()), AddressVo.class);
                userVo.setAddresses(addressVos);
            }
            userVos.add(userVo);
        }

        return userVos;
    }

    @Override
    public PageResultVo<UserVo> queryUsersByPage(UserDto userDto) {
        // 1. 构造分页条件，注意要使用 mp 提供的Page类
        Page<UserPo> page = Page.of(userDto.getPageNo(), userDto.getPageSize());

        if (StrUtil.isNotBlank(userDto.getSortBy())) { //  StrUtil  糊涂工具中的 字符串工具类。这里就是判断字符串是否为空
            page.addOrder(new OrderItem(userDto.getSortBy(), userDto.isAsc()));
        } else {
            page.addOrder(new OrderItem("update_time", false));
        }

        // 2. 构造查询条件并执行分页查询
        lambdaQuery()
                .like(StrUtil.isNotBlank(userDto.getName()), UserPo::getName, userDto.getName())
                .like(StrUtil.isNotBlank(userDto.getPassword()), UserPo::getPassword, userDto.getPassword())
                .page(page); // 执行分页查询，查询的结果会放回到 page 对象

        // 3. 准备返回的vo对象
        PageResultVo<UserVo> resultVo = new PageResultVo<>();
        resultVo.setTotal((int) page.getTotal());  // 总数
        resultVo.setPages((int) page.getPages());  // 总页数
        resultVo.setList(BeanUtil.copyToList(page.getRecords(), UserVo.class));

        return resultVo;
    }
}
