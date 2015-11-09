package com.tw.mapper;

import com.tw.domain.InstalmentPolicy;
import org.apache.ibatis.annotations.Param;

public interface InstalmentPolicyMapper {
    int createInstalmentPolicy(@Param("policy") InstalmentPolicy instalmentPolicy);

    InstalmentPolicy findInstalmentPolicyById(@Param("policyId") int policyId);
}
