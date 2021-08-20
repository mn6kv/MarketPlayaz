package org.example.services;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;

import java.util.List;

public interface OrderService {
  public List<OrderDto> getOrdersWithArgs(RestCallArgs callArgs) throws NotFoundException;
}
