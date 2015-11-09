package com.tw.mapper;

import com.tw.domain.Consumer;
import com.tw.domain.PaymentRequest;
import com.tw.domain.RequestStatus;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface PaymentRequestMapper {
    int createPaymentRequest(@Param("request") PaymentRequest paymentRequest);

    PaymentRequest findPaymentRequestById(@Param("paymentRequestId") int paymentRequestId);

    int updatePaymentRequest(@Param("request") PaymentRequest paymentRequest);

    List<PaymentRequest> findRequestsByRange(@Param("from") Date from, @Param("to") Date to,
                                             @Param("consumer") Consumer consumer,
                                             @Param("status") RequestStatus status);
}
