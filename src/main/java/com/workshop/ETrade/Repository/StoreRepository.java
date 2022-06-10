package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.StoreDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<StoreDTO, String> {
}
