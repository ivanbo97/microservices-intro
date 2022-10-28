package com.ivanboyukliev.notification.service;

import com.ivanboyukliev.notification.api.NotificationRequest;
import com.ivanboyukliev.notification.model.Notification;
import com.ivanboyukliev.notification.model.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired private NotificationRepository notificationRepository;

  @Override
  public Notification getNotificationById(Integer notificationId) {
    return notificationRepository
        .findById(notificationId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format("Customer with id [%d] doesn't exist", notificationId)));
  }

  @Override
  public void createNotification(NotificationRequest notificationRequest) {
    notificationRepository.save(
        Notification.builder()
            .message(notificationRequest.getMessage())
            .sentAt(LocalDateTime.now())
            .sender(notificationRequest.getSender())
            .toCustomerId(notificationRequest.getCustomerId())
            .toCustomerEmail(notificationRequest.getCustomerEmail())
            .build());
  }
}
