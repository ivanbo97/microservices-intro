package com.ivanboyukliev.customer.service;

import com.ivanboyukliev.customer.api.CustomerRegistrationRequest;
import com.ivanboyukliev.customer.model.Customer;
import com.ivanboyukliev.customer.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  @Autowired CustomerRepository customerRepository;

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
    return customerRepository.save(customer);
  }
}
