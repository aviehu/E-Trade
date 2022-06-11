package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreBasketRepository extends MongoRepository<StoreBasketDTO, String> {
}
