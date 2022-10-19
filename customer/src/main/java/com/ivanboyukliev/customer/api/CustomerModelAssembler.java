package com.ivanboyukliev.customer.api;

import com.ivanboyukliev.customer.api.CustomerApi;
import com.ivanboyukliev.customer.api.CustomerModel;
import com.ivanboyukliev.customer.model.Customer;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CustomerModelAssembler
    extends RepresentationModelAssemblerSupport<Customer, CustomerModel> {
  public CustomerModelAssembler() {
    super(CustomerApi.class, CustomerModel.class);
  }

  @Override
  public CustomerModel toModel(Customer customer) {
    return CustomerModel.builder()
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .build()
        .add(linkTo(methodOn(CustomerApi.class).getCustomerById(customer.getId())).withSelfRel());
  }
}
