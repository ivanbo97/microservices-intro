package com.ivanboyukliev.notification.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationModel extends RepresentationModel<NotificationModel> {
  Integer toCustomerId;
  String toCustomerEmail;
  String message;
}
