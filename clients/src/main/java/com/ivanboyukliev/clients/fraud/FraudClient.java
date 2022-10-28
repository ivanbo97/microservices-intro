package com.ivanboyukliev.clients.fraud;

import com.ivanboyukliev.fraud.api.FraudCheckModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {

  @GetMapping(path = "api/v1/fraud-check/{customerId}")
  public FraudCheckModel isFraudster(@PathVariable("customerId") Integer customerId);
}
