package com.ivanboyukliev.customer.api;

import com.ivanboyukliev.customer.model.Customer;
import com.ivanboyukliev.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerApi {

  @Autowired CustomerService customerService;

  @Autowired CustomerModelAssembler customerModelAssembler;

  @GetMapping("/{customerId}")
  @ResponseStatus(HttpStatus.OK)
  // @CustomerRead this will be permission
  @Operation(summary = "Gets customer by id")
  public Customer getCustomerById(final @PathVariable Integer customerId) {
    return customerService.getCustomerById(customerId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  // @CustomerCreate this will be permission
  @Operation(summary = "Registers a new customer")
  public ResponseEntity<CustomerModel> registerCustomer(
      @RequestBody CustomerRegistrationRequest registrationRequest) {
    var createdCustomer = customerService.registerCustomer(registrationRequest);
    return new ResponseEntity<>(
        customerModelAssembler.toModel(createdCustomer), HttpStatus.CREATED);
  }
}
