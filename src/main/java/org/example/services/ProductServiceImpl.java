package org.example.services;

import org.example.models.Product;
import org.example.repositories.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductJpaRepository productJpaRepository;

  @Override
  public List<Product> getProductsByIds(List<Long> ids) {
    return ids.stream().map(this::getProductById).collect(Collectors.toList());
  }

  private Product getProductById(Long id) {
    return productJpaRepository.findById(id).orElse(null);
  }
}
