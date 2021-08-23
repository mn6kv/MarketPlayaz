package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Order;
import org.example.models.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
  private Long id;
  private Integer number;
  private String buyerEmail;
  private LocalDateTime date;
  private List<ProductDto> products;

  public static OrderDto from(Order order) {
    return OrderDto.builder()
        .id(order.getId())
        .number(order.getNumber())
        .buyerEmail(order.getBuyerEmail())
        .date(order.getDate())
        .products(ProductDto.from(order.getProducts()))
        .build();
  }

  public static List<OrderDto> from(List<Order> orders) {
    return orders.stream().map(OrderDto::from).collect(Collectors.toList());
  }
}
