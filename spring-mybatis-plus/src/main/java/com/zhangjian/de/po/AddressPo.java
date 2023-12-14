package com.zhangjian.de.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhangjian.enums.BoolStatus;
import lombok.Data;


@Data
@TableName("address")
public class AddressPo {

    @TableId( type = IdType.AUTO )
    private Integer id;

    private Integer userId;

    private String province;

    private String city;

    private String town;

    private String mobile;

    private String street;

    private String concat;

    private BoolStatus isDefault;

    private String notes;

    private BoolStatus isDelete;
}
