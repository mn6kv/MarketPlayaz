package org.example.services;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.models.Order;
import org.example.repositories.OrderJpaRepository;
import org.example.repositories.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderJpaRepository orderRepository;
  @Autowired
  private ProductJpaRepository productRepository;
  @Autowired
  private ProductService productService;

  @Override
  public ResponseEntity<List<OrderDto>> getAllOrders() {
    List<Order> orders = orderRepository.findAll();
    return new ResponseEntity<>(OrderDto.from(orders), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<OrderDto>> getOrderByEmail(String buyersEmail) {
    return ResponseEntity.ok(OrderDto.from(orderRepository.findOrdersByBuyerEmail(buyersEmail)));
  }

  @Override
  public ResponseEntity<List<OrderDto>> getOrderByArticle(Long article) {
    List<Order> orders = orderRepository.findOrdersByProductsContains(
        productRepository.findProductByArticle(article)
            .orElseThrow(() -> new RuntimeException("Product by article not found")));
    return ResponseEntity.ok(OrderDto.from(orders));
  }

  @Override
  public ResponseEntity<List<OrderDto>> getOrderByInterval(LocalDateTime startDate,
                                                           LocalDateTime endDate) {
    return ResponseEntity.ok(OrderDto.from(orderRepository.findOrdersByDateBetween(startDate, endDate)));
  }

  @Override
  public ResponseEntity<OrderDto> saveOrderWithArgs(String buyersEmail, LocalDateTime date,
                                                   List<Long> productsIds) {
    return new ResponseEntity<>(OrderDto.from(orderRepository.save(Order.builder()
        .buyerEmail(buyersEmail)
        .number(date.hashCode())
        .products(productService.getProductsByIds(productsIds))
        .date(date)
        .build())), HttpStatus.OK);
  }
}
