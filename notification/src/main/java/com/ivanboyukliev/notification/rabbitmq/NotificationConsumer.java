package com.ivanboyukliev.notification.rabbitmq;

import com.ivanboyukliev.notification.api.NotificationRequest;
import com.ivanboyukliev.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {

  @Autowired private NotificationService notificationService;

  @RabbitListener(queues = "notification.queue")
  public void consume(NotificationRequest notificationRequest) {
    log.info("Consume from {} queue", notificationRequest);
    notificationService.createNotification(notificationRequest);
  }
}
