package com.tw.domain;

import com.tw.mapper.InstalmentItemMapper;
import com.tw.mapper.InstalmentRequestMapper;

import java.sql.Date;
import java.util.List;

public class InstalmentRequestRepositoryImpl implements InstalmentRequestRepository {
    private final InstalmentRequestMapper instalmentRequestMapper;
    private final InstalmentItemMapper instalmentItemMapper;

    public InstalmentRequestRepositoryImpl(InstalmentRequestMapper instalmentRequestMapper, InstalmentItemMapper instalmentItemMapper) {

        this.instalmentRequestMapper = instalmentRequestMapper;
        this.instalmentItemMapper = instalmentItemMapper;
    }

    @Override
    public InstalmentRequest findInstalmentRequestById(int requestId) {
        return instalmentRequestMapper.findInstalmentRequestById(requestId);
    }

    @Override
    public void updateInstalmentRequest(InstalmentRequest instalmentRequest) {
        instalmentRequestMapper.updateInstalmentRequest(instalmentRequest);
    }

    @Override
    public List<InstalmentItem> findConfirmedInstalmentsByRepaymentDay(Date repaymentDay, Consumer consumer) {
        return instalmentRequestMapper.findInstalmentItemByRepaymentDay(consumer, repaymentDay, RequestStatus.CONFIRMED);
    }

    @Override
    public InstalmentRequest createInstalmentRequest(InstalmentRequest instalmentRequest) {
        instalmentRequestMapper.createInstalmentRequest(instalmentRequest);
        for (InstalmentItem item : instalmentRequest.getItems()) {
            instalmentItemMapper.createInstalmentItem(item);
        }
        return instalmentRequest;
    }
}
