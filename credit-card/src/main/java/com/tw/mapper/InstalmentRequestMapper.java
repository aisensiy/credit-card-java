package com.tw.mapper;

import com.tw.domain.InstalmentRequest;
import org.apache.ibatis.annotations.Param;

public interface InstalmentRequestMapper {
    int createInstalmentRequest(@Param("instalmentRequest") InstalmentRequest instalmentRequest);

    InstalmentRequest findInstalmentRequestById(@Param("id") int requestId);

    void updateInstalmentRequest(@Param("instalmentRequest") InstalmentRequest instalmentRequest);
}
