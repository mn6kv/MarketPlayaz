package org.example.services;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
  ResponseEntity<List<OrderDto>> getOrderByEmail(String buyersEmail);
  ResponseEntity<List<OrderDto>> getOrderByArticle(Long article);
  ResponseEntity<List<OrderDto>> getOrderByInterval(LocalDateTime startDate, LocalDateTime endDate);
  ResponseEntity<OrderDto> saveOrderWithArgs(String buyersEmail, LocalDateTime date, List<Long> products);
  ResponseEntity<List<OrderDto>> getAllOrders();
}
