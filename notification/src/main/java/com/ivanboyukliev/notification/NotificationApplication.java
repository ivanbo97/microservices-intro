package com.ivanboyukliev.notification;

import com.ivanboyukliev.amqp.RabbitMqMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
    scanBasePackages = {"com.ivanboyukliev.notification", "com.ivanboyukliev.amqp"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.ivanboyukliev.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class NotificationApplication {
  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

  //  @Bean
  //  CommandLineRunner commandLineRunner(
  //      RabbitMqMessageProducer producer, NotificationConfig notificationConfig) {
  //    var queueName = notificationConfig.getNotificationQueue();
  //    return args -> {
  //      producer.publish(
  //          new Person("Ivan Doe", 123),
  //          notificationConfig.getInternalExchange(),
  //          notificationConfig.getInternalNotificationRoutingKey());
  //    };
  //  }
  //
  //  record Person(String name, int age) {}
}
