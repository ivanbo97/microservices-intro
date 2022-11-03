package com.ivanboyukliev.fraud.api;

import com.ivanboyukliev.fraud.model.FraudCheckHistory;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FraudCheckModelAssembler
    extends RepresentationModelAssemblerSupport<FraudCheckHistory, FraudCheckModel> {
  public FraudCheckModelAssembler() {
    super(FraudCheckHistoryApi.class, FraudCheckModel.class);
  }

  @Override
  public FraudCheckModel toModel(FraudCheckHistory fraudCheck) {
    return FraudCheckModel.builder()
        .customerId(fraudCheck.getCustomerId())
        .isFraudster(fraudCheck.getIsFraudster())
        .createdAt(fraudCheck.getCreatedAt())
        .build();
  }
}
