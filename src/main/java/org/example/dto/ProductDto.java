package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Order;
import org.example.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
  private Long id;
  private String name;
  private Double price;
  private String article;

  public static ProductDto from(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .article(product.getArticle())
        .build();
  }

  public static List<ProductDto> from(List<Product> orders) {
    return orders.stream().map(ProductDto::from).collect(Collectors.toList());
  }
}
