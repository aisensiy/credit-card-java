package com.tw.domain;

public interface InstalmentRequestRepository {
    InstalmentRequest findInstalmentRequestById(int requestId);

    void updateInstalmentRequest(InstalmentRequest instalmentRequest);
}
