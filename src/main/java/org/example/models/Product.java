package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double price;
  private Long article;
  @Enumerated(value = EnumType.STRING)
  private State state;
  @ManyToMany
  @JoinTable(name = "order_product",
      joinColumns = {@JoinColumn(name = "market_order_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
  private List<Order> orders;

  public enum State {
    ACTIVE, DELETED
  }
}
