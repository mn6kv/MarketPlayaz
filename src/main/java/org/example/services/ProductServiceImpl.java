package org.example.services;

import org.example.models.Product;
import org.example.repositories.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductJpaRepository productJpaRepository;

  @Override
  public List<Product> getProductsByIds(List<Long> ids) {
    List<Product> products = new ArrayList<>();
    for (Long id : ids) {
      Product product = getProductById(id);
      if (product != null) {
        products.add(product);
      }
    }
    return products;
  }

  private Product getProductById(Long id) {
    return productJpaRepository.findById(id).orElse(null);
  }
}
