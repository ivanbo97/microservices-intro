package com.ivanboyukliev.fraud.service;

import com.ivanboyukliev.fraud.model.FraudCheckHistory;

public interface FraudCheckHistoryService {

  boolean isFraudulentCustomer(Integer customerId);

  FraudCheckHistory getByCustomerId(Integer customerId);
}
