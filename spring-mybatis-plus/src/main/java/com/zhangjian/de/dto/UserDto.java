package com.zhangjian.de.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode( callSuper = true )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户表单实体") // swagger 采集注解
public class UserDto extends PageDto {

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("性别。0 女；1 男")
    private byte gender;

    @ApiModelProperty("入职日期")
    private LocalDate entryDate;

    @ApiModelProperty("薪资")
    private Integer salary;
}
