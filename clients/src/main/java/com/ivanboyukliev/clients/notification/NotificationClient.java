package com.ivanboyukliev.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("notification")
public interface NotificationClient {

  @PostMapping(path = "api/v1/notifications")
  public void saveNotification(final @Valid @RequestBody NotificationRequest notificationRequest);
}
