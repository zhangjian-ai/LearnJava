package com.zhangjian.de.vo;

import com.zhangjian.de.po.AccountInfoPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户VO实体")
public class UserVo {

    @ApiModelProperty("id") // swagger 采集注解
    private int id;

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("性别。0 女；1 男")
    private byte gender;

    @ApiModelProperty("头像图片")
    private String image;

    @ApiModelProperty("岗位")
    private byte job;

    @ApiModelProperty("入职日期")
    private LocalDate entryDate;

    @ApiModelProperty("部门ID")
    private int deptId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("薪资")
    private Integer salary;

    @ApiModelProperty("用户地址信息") // 新增
    private List<AddressVo> addresses;

    @ApiModelProperty("账户信息") // 新增
    private AccountInfoPo accountInfo;
}
