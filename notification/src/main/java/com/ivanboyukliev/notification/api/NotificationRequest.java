package com.ivanboyukliev.notification.api;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequest {
  @NotNull Integer customerId;
  @Email String customerEmail;
  @NotEmpty String message;
  @NotEmpty String sender;
}
