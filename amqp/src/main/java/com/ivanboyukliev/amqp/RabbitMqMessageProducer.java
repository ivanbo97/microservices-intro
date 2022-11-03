package com.ivanboyukliev.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqMessageProducer {
  @Autowired private AmqpTemplate amqpTemplate;

  public void publish(Object payload, String exchange, String routingKey) {
    log.info(
        "Publishing to {} exchange using routing key {}. Payload: {}",
        exchange,
        routingKey,
        payload);
    amqpTemplate.convertAndSend(exchange, routingKey, payload);
    log.info(
        "Published to {} exchange using routing key {}. Payload: {}",
        exchange,
        routingKey,
        payload);
  }
}
