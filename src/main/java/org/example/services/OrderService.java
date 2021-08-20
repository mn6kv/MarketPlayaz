package org.example.services;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;
import org.example.models.Order;

import java.util.List;

public interface OrderService {
  List<OrderDto> getOrdersWithArgs(RestCallArgs callArgs) throws NotFoundException;
  OrderDto putOrderWithArgs(Order callArgs);
}
