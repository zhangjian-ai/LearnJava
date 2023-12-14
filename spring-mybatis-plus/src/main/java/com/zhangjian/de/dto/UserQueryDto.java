package com.zhangjian.de.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "员工条件查询Dto")
public class UserQueryDto {
    @ApiModelProperty("用户名关键字")
    private String username;

    @ApiModelProperty("性别。0 女；1 男")
    private Byte gender;

    @ApiModelProperty("入职时间起始值")
    private LocalDate start;

    @ApiModelProperty("入职时间截止日")
    private LocalDate end;
}
