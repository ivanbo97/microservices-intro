package com.ivanboyukliev.customer.service;

import com.ivanboyukliev.amqp.RabbitMqMessageProducer;
import com.ivanboyukliev.clients.fraud.FraudCheckModel;
import com.ivanboyukliev.clients.fraud.FraudClient;
import com.ivanboyukliev.clients.notification.NotificationRequest;
import com.ivanboyukliev.customer.api.CustomerRegistrationRequest;
import com.ivanboyukliev.customer.model.Customer;
import com.ivanboyukliev.customer.model.CustomerRepository;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  @Autowired private CustomerRepository customerRepository;

  @Autowired private RestTemplate restTemplate;

  @Autowired private FraudClient fraudClient;

  @Autowired private RabbitMqMessageProducer messageProducer;

  @Override
  public Customer getCustomerById(Integer customerId) {
    return customerRepository
        .findById(customerId)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format("Customer with id [%d] doesn't exist", customerId)));
  }

  @Override
  public Customer registerCustomer(CustomerRegistrationRequest registrationRequest) {
    var customer =
        Customer.builder()
            .firstName(registrationRequest.firstName())
            .lastName(registrationRequest.lastName())
            .email(registrationRequest.email())
            .build();

    customerRepository.saveAndFlush(customer);
    FraudCheckModel fraudCheck = fraudClient.isFraudster(customer.getId());

    if (fraudCheck.getIsFraudster()) {
      throw new IllegalStateException("Customer is a fraudster");
    }

    NotificationRequest notificationRequest =
        NotificationRequest.builder()
            .customerId(customer.getId())
            .customerEmail(customer.getEmail())
            .message("Congrats! Successful registration! Our service made it!")
            .sender("Customer Microservice")
            .build();

    messageProducer.publish(
        notificationRequest, "internal.exchange", "rabbitmq.routing-keys.internal-notification");
    return customer;
  }
}
