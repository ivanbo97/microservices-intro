package com.ivanboyukliev.fraud.service;

import com.ivanboyukliev.fraud.model.FraudCheckHistory;

public interface FraudCheckHistoryService {

  FraudCheckHistory isFraudulentCustomer(Integer customerId);

  FraudCheckHistory getByCustomerId(Integer customerId);
}
