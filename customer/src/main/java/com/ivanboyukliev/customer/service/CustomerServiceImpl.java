package com.ivanboyukliev.customer.service;

import com.ivanboyukliev.clients.fraud.FraudClient;
import com.ivanboyukliev.clients.notification.NotificationClient;
import com.ivanboyukliev.customer.api.CustomerRegistrationRequest;
import com.ivanboyukliev.customer.model.Customer;
import com.ivanboyukliev.customer.model.CustomerRepository;
import com.ivanboyukliev.fraud.api.FraudCheckModel;
import com.ivanboyukliev.notification.api.NotificationRequest;
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

  @Autowired private NotificationClient notificationClient;

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

    notificationClient.saveNotification(
        NotificationRequest.builder()
            .customerId(customer.getId())
            .customerEmail(customer.getEmail())
            .message("Congrats! Successful registration! Our service made it!")
            .sender("Customer Microservice")
            .build());
    return customer;
  }
}
