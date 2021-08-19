package org.example.services;

import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  @Override
  public List<OrderDto> getOrdersWithArgs(RestCallArgs callArgs) {
    String userEmail = callArgs.getUsersEmail();
    String article = callArgs.getArticle();
    Date startDate = callArgs.getStart_date();
    Date endDate = callArgs.getEnd_date();
    return new ArrayList<OrderDto>();
  }
}
