package com.zhangjian.music.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangjian.music.enums.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页查询表单实体")
public class PageDto {

    @ApiModelProperty("当前页码")
    private Integer pageNo;

    @ApiModelProperty("当前页大小")
    private Integer pageSize;

    @ApiModelProperty("排序规则 asc desc non")
    private Order order;

    // 排序字段。当 order 取值为 ASC/DESC 时才有效
    @ApiModelProperty("排序的字段")
    private String orderField;

}
