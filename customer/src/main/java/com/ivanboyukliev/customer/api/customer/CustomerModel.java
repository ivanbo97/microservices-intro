package com.ivanboyukliev.customer.api.customer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerModel extends RepresentationModel<CustomerModel> {
  String firstName;
  String lastName;
  String email;
}
