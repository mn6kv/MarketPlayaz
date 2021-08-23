package org.example.controllers;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;
import org.example.models.Product;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.example.models.Order;

@RestController
public class OrderRestController {

  @Autowired
  private OrderService orderService;

  @GetMapping("/orders")
  public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(name = "buyers_email", required = false) String buyersEmail,
                                                  @RequestParam(name = "article", required = false) String article,
                                                  @RequestParam(name = "start_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                        LocalDateTime start_date,
                                                  @RequestParam(name = "end_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_date) {
    RestCallArgs callArgs = new RestCallArgs(buyersEmail, article, start_date, end_date);
    List<OrderDto> orders = null;
    try {
      orders = orderService.getOrdersWithArgs(callArgs);
    }
    catch (NotFoundException e) {
      throw new IllegalArgumentException(e);
    }
    return orders != null
        ? new ResponseEntity<>(orders, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/orders")
  public ResponseEntity<OrderDto> addOrder(@RequestParam(name = "buyers_email", required = false) String buyersEmail,
                                           @RequestParam(name = "products") List<Product> products,
                                           @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                 LocalDateTime date) {
    Order order = Order.builder()
        .buyerEmail(buyersEmail)
        .number(date.hashCode())
        .products(products)
        .date(date)
        .build();
    OrderDto orderDto = orderService.putOrderWithArgs(order);
    return orderDto != null
        ? new ResponseEntity<>(orderDto, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
