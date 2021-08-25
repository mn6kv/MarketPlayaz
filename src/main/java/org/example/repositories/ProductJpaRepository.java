package org.example.repositories;

import org.example.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
  Optional<Product> findProductByArticle(Long article);
}
