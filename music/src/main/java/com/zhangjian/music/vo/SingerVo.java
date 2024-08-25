package com.zhangjian.music.vo;

import com.zhangjian.music.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerVo {

    private Integer id;

    private String name;

    private Gender gender;

    private String pic;

    private LocalDate birth;

    private String location;

    private String introduction;
}
