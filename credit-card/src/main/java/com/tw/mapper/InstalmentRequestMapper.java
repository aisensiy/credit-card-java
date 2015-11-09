package com.tw.mapper;

import com.tw.domain.Consumer;
import com.tw.domain.InstalmentItem;
import com.tw.domain.InstalmentRequest;
import com.tw.domain.RequestStatus;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface InstalmentRequestMapper {
    int createInstalmentRequest(@Param("instalmentRequest") InstalmentRequest instalmentRequest);

    InstalmentRequest findInstalmentRequestById(@Param("id") int requestId);

    void updateInstalmentRequest(@Param("instalmentRequest") InstalmentRequest instalmentRequest);

    List<InstalmentItem> findInstalmentItemByRepaymentDay(@Param("consumer") Consumer consumer,
                                                          @Param("day") Date repaymentDay,
                                                          @Param("status") RequestStatus confirmed);
}
