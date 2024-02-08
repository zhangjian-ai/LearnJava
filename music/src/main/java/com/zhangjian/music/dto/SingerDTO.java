package com.zhangjian.music.dto;

import com.zhangjian.music.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
@ApiModel(description = "歌手表单实体")
public class SingerDTO extends PageDTO {

    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private Gender gender;

    @ApiModelProperty("头像地址")
    private String pic;

    @ApiModelProperty("出生日期")
    private LocalDate birth;

    @ApiModelProperty("详细地址")
    private String location;

    @ApiModelProperty("歌手简介")
    private String introduction;
}
