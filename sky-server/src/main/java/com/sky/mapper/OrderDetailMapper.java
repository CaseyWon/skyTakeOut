package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    //用户下单（订单明细）批量插入
    void insertBatch(List<OrderDetail> orderDetailList);
}
