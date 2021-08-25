package org.example.services;

import org.example.models.Product;

import java.util.List;

public interface ProductService {
  List<Product> getProductsByIds(List<Long> ids);
}
