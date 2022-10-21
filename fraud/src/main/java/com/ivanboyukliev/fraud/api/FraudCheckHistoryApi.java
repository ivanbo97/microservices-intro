package com.ivanboyukliev.fraud.api;

import com.ivanboyukliev.fraud.service.FraudCheckHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud")
public class FraudCheckHistoryApi {

  @Autowired private FraudCheckHistoryService fraudCheckHistoryService;

  @Autowired private FraudCheckModelAssembler fraudCheckModelAssembler;

  @GetMapping("/{customerId}")
  public FraudCheckModel isFraudster(@PathVariable Integer customerId) {
    return fraudCheckModelAssembler.toModel(fraudCheckHistoryService.getByCustomerId(customerId));
  }
}
