package com.tw.mapper;

import com.tw.domain.BillItem;
import org.apache.ibatis.annotations.Param;

public interface BillItemMapper {
    int createBillItem(@Param("item") BillItem item);
}
