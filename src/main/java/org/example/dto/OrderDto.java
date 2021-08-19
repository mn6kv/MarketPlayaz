package org.example.dto;

import org.example.models.Product;

import java.util.Date;
import java.util.List;

public class OrderDto {
  private Long id;
  private String number;
  private String buyerEmail;
  private Date date;
  private List<Product> product;
}
