package com.tw.domain;

import com.tw.mapper.InstalmentPolicyMapper;

import javax.ws.rs.core.MultivaluedMap;

public class InstalmentPolicyRepositoryImpl implements InstalmentPolicyRepository {
    private InstalmentPolicyMapper mapper;

    public InstalmentPolicyRepositoryImpl(InstalmentPolicyMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public InstalmentPolicy createInstalmentPolicy(MultivaluedMap<String, String> map) {
        InstalmentPolicy instalmentPolicy = new InstalmentPolicy(
                Integer.parseInt(map.getFirst("term")),
                Integer.parseInt(map.getFirst("commission")));
        mapper.createInstalmentPolicy(instalmentPolicy);
        return instalmentPolicy;
    }

    @Override
    public InstalmentPolicy findInstalmentPolicyById(int policyId) {
        return mapper.findInstalmentPolicyById(policyId);
    }
}
