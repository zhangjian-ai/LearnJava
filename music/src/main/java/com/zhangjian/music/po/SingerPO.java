package com.zhangjian.music.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhangjian.music.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( value = "singer", autoResultMap = true )
public class SingerPO {

    @TableId( type = IdType.AUTO )
    private Integer id;

    private String name;

    private Gender gender;

    private String pic;

    private LocalDate birth;

    private String location;

    private String introduction;
}
