package com.ivanboyukliev.fraud.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudCheckModel extends RepresentationModel<FraudCheckModel> {
  private Integer customerId;
  private Boolean isFraudster;
  private LocalDateTime createdAt;
}
