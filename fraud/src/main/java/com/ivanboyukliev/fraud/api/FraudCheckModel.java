package com.ivanboyukliev.fraud.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
public class FraudCheckModel extends RepresentationModel<FraudCheckModel> {
  private Integer customerId;
  private Boolean isFraudster;
  private LocalDateTime createdAt;
}
