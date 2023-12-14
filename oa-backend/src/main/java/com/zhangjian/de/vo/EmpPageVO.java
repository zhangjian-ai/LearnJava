package com.zhangjian.de.vo;

import com.zhangjian.de.po.EmpPO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 员工分页查询页面数据类
 */
@Getter
@Setter
public class EmpPageVO {
    private long total; // 员工总数
    private List<EmpPO> rows; // 当前页的员工信息列表
}
