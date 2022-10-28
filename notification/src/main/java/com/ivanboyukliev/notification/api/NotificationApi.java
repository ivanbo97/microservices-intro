package com.ivanboyukliev.notification.api;

import com.ivanboyukliev.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/notifications")
public class NotificationApi {

  @Autowired private NotificationService notificationService;

  @Autowired private NotificationModelAssembler notificationModelAssembler;

  @GetMapping("/{notificationId}")
  public NotificationModel getNotificationById(@PathVariable Integer notificationId) {
    log.info("Search initiated for notification with id {customerId}", notificationId);
    return notificationModelAssembler.toModel(
        notificationService.getNotificationById(notificationId));
  }

  @PostMapping
  public void saveNotification(final @Valid @RequestBody NotificationRequest notificationRequest) {
    notificationService.createNotification(notificationRequest);
  }
}
