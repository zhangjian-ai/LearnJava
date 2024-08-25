package com.zhangjian.music.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "song", autoResultMap = true)
public class SongPo {

    @TableId(type = IdType.AUTO)
    private int id;

    private int singerId;

    private String name;

    private String introduction;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String pic;

    private String lyric;

    private String url;
}
