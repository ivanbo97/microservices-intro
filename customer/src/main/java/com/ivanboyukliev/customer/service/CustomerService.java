package com.ivanboyukliev.customer.service;

import com.ivanboyukliev.customer.api.CustomerRegistrationRequest;
import com.ivanboyukliev.customer.model.Customer;

public interface CustomerService {

  Customer getCustomerById(Integer customerId);

  Customer registerCustomer(CustomerRegistrationRequest registrationRequest);
}
