package com.tw.domain;

import com.tw.mapper.InstalmentPolicyMapper;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class InstalmentPolicyRepositoryImplTest extends RepositoryTestBase {

    private InstalmentPolicyRepository instalmentPolicyRepository;
    private MultivaluedMap<String, String> map;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        final InstalmentPolicyMapper mapper = sqlSession.getMapper(InstalmentPolicyMapper.class);
        instalmentPolicyRepository = new InstalmentPolicyRepositoryImpl(mapper);
        map = new MultivaluedHashMap<>();
        map.putSingle("term", "6");
        map.putSingle("commission", "1");
    }

    @Test
    public void createInstalmentPolicy() throws Exception {
        final InstalmentPolicy instalmentPolicy = instalmentPolicyRepository.createInstalmentPolicy(map);
        assertThat(instalmentPolicy.getId(), not(0));
    }
}