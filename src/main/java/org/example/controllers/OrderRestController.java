package org.example.controllers;

import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class OrderRestController {

  @Autowired
  private OrderService orderService;

  //todo часовой пояс

  @GetMapping("/orders")
  public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(name = "users_email", required = false) String usersEmail,
                                                  @RequestParam(name = "article", required = false) String article,
                                                  @RequestParam(name = "start_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        Date start_date,
                                                  @RequestParam(name = "end_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end_date) {
    RestCallArgs callArgs = new RestCallArgs(usersEmail, article, start_date, end_date);
    System.out.println(start_date.before(end_date));
    System.out.println(start_date);
    List<OrderDto> orders = orderService.getOrdersWithArgs(callArgs);
    return orders != null
        ? new ResponseEntity<>(orders, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
