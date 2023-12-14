package com.zhangjian.de.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页实体")
public class PageDto {
    // 页码和size给个默认值，
    @ApiModelProperty("页码")
    private Integer pageNo = 1;

    @ApiModelProperty("每页大小")
    private Integer pageSize = 5;

    @ApiModelProperty("排序字段")
    private String sortBy;

    // 需要注意的是，如果 布尔类型属性以 is开头时，lombok 生成的get方法，在老版本不是以get开头的，直接就是属性名
    @ApiModelProperty("是否升序")
    private boolean isAsc;
}
