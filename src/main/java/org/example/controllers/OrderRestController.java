package org.example.controllers;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderRestController {

  @Autowired
  private OrderService orderService;

  @GetMapping("/orders")
  public ResponseEntity<List<OrderDto>> getOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/orders/email")
  public ResponseEntity<List<OrderDto>> getOrders(
      @RequestParam("buyers_email") String buyersEmail) {
    return orderService.getOrderByEmail(buyersEmail);
  }

  @GetMapping("/orders/article")
  public ResponseEntity<List<OrderDto>> getOrders(@RequestParam("article") Long article) {
      return orderService.getOrderByArticle(article);

  }

  @GetMapping("/orders/interval")
  public ResponseEntity<List<OrderDto>> getOrders(
      @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime startDate,
      @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime endDate) {
    return orderService.getOrderByInterval(startDate, endDate);
  }

  @PostMapping("/orders")
  public ResponseEntity<OrderDto> addOrder(@RequestBody List<Long> productsIds,
                                           @RequestParam(name = "buyers_email", required = false)
                                               String buyersEmail,
                                           @RequestParam(name = "date", required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                               LocalDateTime date) {
    return orderService.putOrderWithArgs(buyersEmail, date, productsIds);
  }
}
