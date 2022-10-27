package com.ivanboyukliev.fraud.service;

import com.ivanboyukliev.fraud.model.FraudCheckHistory;
import com.ivanboyukliev.fraud.model.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryServiceImpl implements FraudCheckHistoryService {

  @Autowired private final FraudCheckHistoryRepository fraudCheckRepo;

  @Override
  public FraudCheckHistory isFraudulentCustomer(Integer customerId) {
    return fraudCheckRepo.save(
        FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .createdAt(LocalDateTime.now())
            .build());
  }

  @Override
  public FraudCheckHistory getByCustomerId(Integer customerId) {
    return fraudCheckRepo.getByCustomerId(customerId);
  }
}
