package org.example.controllers;

import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class OrderRestController {

  @Autowired
  private OrderService orderService;

  //todo проверить работооспособность Date
  //todo посмотреть как принимать с возможностью null PathVariable

  @GetMapping("/orders/{users_email}/{article}/{start_date}/{end_date}")
  public ResponseEntity<List<OrderDto>> getOrders(@PathVariable("users_email") String usersEmail,
                                                  @PathVariable("article") String article,
                                                  @PathVariable("start_date") Date start_date,
                                                  @PathVariable("end_date") Date end_date) {
    RestCallArgs callArgs = new RestCallArgs(usersEmail, article, start_date, end_date);
    List<OrderDto> orders = orderService.getOrdersWithArgs(callArgs);
    return orders != null
        ? new ResponseEntity<>(orders, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
