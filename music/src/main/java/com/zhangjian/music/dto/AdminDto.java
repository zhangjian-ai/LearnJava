package com.zhangjian.music.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel( description = "管理员登录表单")
public class AdminDto {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("密码")
    private String password;
}
