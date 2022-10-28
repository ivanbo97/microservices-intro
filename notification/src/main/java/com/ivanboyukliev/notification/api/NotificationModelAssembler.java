package com.ivanboyukliev.notification.api;

import com.ivanboyukliev.notification.model.Notification;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class NotificationModelAssembler
    extends RepresentationModelAssemblerSupport<Notification, NotificationModel> {

  public NotificationModelAssembler() {
    super(NotificationApi.class, NotificationModel.class);
  }

  @Override
  public NotificationModel toModel(Notification notification) {
    return NotificationModel.builder()
        .message(notification.getMessage())
        .toCustomerEmail(notification.getToCustomerEmail())
        .toCustomerId(notification.getToCustomerId())
        .build()
        .add(
            linkTo(
                    methodOn(NotificationApi.class)
                        .getNotificationById(notification.getNotificationId()))
                .withSelfRel());
  }
}
