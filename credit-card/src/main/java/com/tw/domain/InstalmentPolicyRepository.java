package com.tw.domain;

import javax.ws.rs.core.MultivaluedMap;

public interface InstalmentPolicyRepository {
    InstalmentPolicy createInstalmentPolicy(MultivaluedMap<String, String> map);

    InstalmentPolicy findInstalmentPolicyById(int policyId);
}
