package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Stores.DiscountDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscountRepository extends MongoRepository<DiscountDTO, String> {
}
