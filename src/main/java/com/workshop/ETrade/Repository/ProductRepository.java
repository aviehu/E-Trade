package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
