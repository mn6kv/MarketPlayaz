package org.example.services;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.dto.ProductDto;
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
    List<Order> orders = orderRepository.findOrdersByBuyerEmail(buyersEmail);
    return !orders.isEmpty()
        ? new ResponseEntity<>(OrderDto.from(orders), HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<List<OrderDto>> getOrderByArticle(Long article) throws NotFoundException {
    List<Order> orders = orderRepository.findOrdersByProductsContains(
        productRepository.findProductByArticle(article)
            .orElseThrow(() -> new NotFoundException("Product by article not found")));
    return !orders.isEmpty()
        ? new ResponseEntity<>(OrderDto.from(orders), HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<List<OrderDto>> getOrderByInterval(LocalDateTime startDate,
                                                           LocalDateTime endDate) {
    List<Order> orders = orderRepository.findOrdersByDateBetween(startDate, endDate);
    return !orders.isEmpty()
        ? new ResponseEntity<>(OrderDto.from(orders), HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<OrderDto> putOrderWithArgs(String buyersEmail, LocalDateTime date,
                                                   List<Long> productsIds) {
    return new ResponseEntity<>(OrderDto.from(orderRepository.save(Order.builder()
        .buyerEmail(buyersEmail)
        .number(date.hashCode())
        .products(productService.getProductsByIds(productsIds))
        .date(date)
        .build())), HttpStatus.OK);
  }
}
