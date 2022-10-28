package com.ivanboyukliev.notification.service;

import com.ivanboyukliev.notification.api.NotificationRequest;
import com.ivanboyukliev.notification.model.Notification;

public interface NotificationService {
  Notification getNotificationById(Integer notificationId);

  void createNotification(NotificationRequest notificationRequest);
}
