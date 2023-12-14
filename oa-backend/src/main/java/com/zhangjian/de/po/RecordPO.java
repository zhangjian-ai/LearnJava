package com.zhangjian.de.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 日志记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordPO {
    private int id;
    private String operate;
    private LocalDateTime createTime;
}
