package org.example.services;

import javassist.NotFoundException;
import org.example.dto.OrderDto;
import org.example.dto.RestCallArgs;
import org.example.models.Order;
import org.example.models.Product;
import org.example.repositories.OrderJpaRepository;
import org.example.repositories.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderJpaRepository orderRepository;
  @Autowired
  private ProductJpaRepository productRepository;

  @Override
  public List<OrderDto> getOrdersWithArgs(RestCallArgs callArgs) throws NotFoundException {
    String buyerEmail = callArgs.getUsersEmail();
    String article = callArgs.getArticle();
    LocalDateTime startDate = callArgs.getStart_date();
    LocalDateTime endDate = callArgs.getEnd_date();

    if (buyerEmail != null) {
      if (article != null) {
        Product productByArticle = productRepository.findProductByArticle(article).
            orElseThrow(() -> new NotFoundException("Product by article not found"));
        if (startDate != null && endDate != null) {
          return OrderDto.from(orderRepository.findOrdersByDateBetweenAndBuyerEmailAndProductsContains(
              startDate,
              endDate,
              buyerEmail,
              productByArticle));
        } else {
          return OrderDto.from(orderRepository.findOrdersByProductsContainsAndBuyerEmail(productByArticle, buyerEmail));
        }
      } else {
        if (startDate != null && endDate != null) {
          return OrderDto.from(orderRepository.findOrdersByDateBetweenAndBuyerEmail(startDate, endDate, buyerEmail));
        } else return OrderDto.from(orderRepository.findOrdersByBuyerEmail(buyerEmail));
      }
    } else if (article != null) {
      Product productByArticle = productRepository.findProductByArticle(article).
          orElseThrow(() -> new NotFoundException("Product by article not found"));
      if (startDate != null && endDate != null) {
        return OrderDto.from(orderRepository.findOrdersByDateBetweenAndProductsContains(startDate, endDate, productByArticle));
      } else return OrderDto.from(orderRepository.findOrdersByProductsContains(productByArticle));
    } else if (startDate != null && endDate != null) {
      return OrderDto.from(orderRepository.findOrdersByDateBetween(startDate, endDate));
    } else return OrderDto.from(orderRepository.findAll());
  }

  @Override
  public OrderDto putOrderWithArgs(Order order) {
    orderRepository.save(order);
    return OrderDto.from(order);
  }
}
