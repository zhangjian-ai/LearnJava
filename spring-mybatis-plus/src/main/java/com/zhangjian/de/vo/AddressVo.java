package com.zhangjian.de.vo;

import com.zhangjian.enums.BoolStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "收货地址VO实体")
public class AddressVo {

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区县")
    private String town;

    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("街道")
    private String street;

    @ApiModelProperty("联系人")
    private String concat;

    @ApiModelProperty("是否是默认收货地址。1 是；0 否")
    private BoolStatus isDefault;

    @ApiModelProperty("备注")
    private String notes;
}
