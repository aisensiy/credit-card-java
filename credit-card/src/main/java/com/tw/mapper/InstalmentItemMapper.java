package com.tw.mapper;

import com.tw.domain.InstalmentItem;
import org.apache.ibatis.annotations.Param;

public interface InstalmentItemMapper {
    int createInstalmentItem(@Param("instalmentItem") InstalmentItem item);
}
