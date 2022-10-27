package com.ivanboyukliev.fraud.api;

import com.ivanboyukliev.fraud.service.FraudCheckHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudCheckHistoryApi {

  @Autowired private FraudCheckHistoryService fraudCheckHistoryService;

  @Autowired private FraudCheckModelAssembler fraudCheckModelAssembler;

  @GetMapping("/{customerId}")
  public FraudCheckModel isFraudster(@PathVariable Integer customerId) {
    log.info("Fraud check request for customer {}", customerId);
    return fraudCheckModelAssembler.toModel(
        fraudCheckHistoryService.isFraudulentCustomer(customerId));
  }
}
