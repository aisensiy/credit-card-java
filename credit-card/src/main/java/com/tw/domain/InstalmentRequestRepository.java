package com.tw.domain;

import java.sql.Date;
import java.util.List;

public interface InstalmentRequestRepository {
    InstalmentRequest findInstalmentRequestById(int requestId);

    void updateInstalmentRequest(InstalmentRequest instalmentRequest);

    List<InstalmentItem> findConfirmedInstalmentsByRepaymentDay(Date repaymentDay, Consumer consumer);

    InstalmentRequest createInstalmentRequest(InstalmentRequest instalmentRequest);
}
