package org.example.repositories;

import org.example.models.Order;
import org.example.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
  List<Order> findOrdersByDateBetween(LocalDateTime start, LocalDateTime end);
  List<Order> findOrdersByBuyerEmail(String email);
  List<Order> findOrdersByProductsContains(Product product);
  List<Order> findOrdersByDateBetweenAndBuyerEmailAndProductsContains(LocalDateTime start,
                                                                      LocalDateTime end,
                                                                      String buyerEmail,
                                                                      Product product);
  List<Order> findOrdersByProductsContainsAndBuyerEmail(Product product, String buyerEmail);
  List<Order> findOrdersByDateBetweenAndBuyerEmail(LocalDateTime start, LocalDateTime end,
                                                   String buyerEmail);
  List<Order> findOrdersByDateBetweenAndProductsContains(LocalDateTime start, LocalDateTime end,
                                                         Product product);
}
